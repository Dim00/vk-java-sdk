package com.vk.api.sdk.events.longpoll;

import com.google.gson.Gson;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.ApiRequest; //
import com.vk.api.sdk.events.EventsHandler;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.callback.longpoll.responses.GetLongPollEventsResponse;
import com.vk.api.sdk.objects.callback.messages.EventClassTypeWrapper;
import com.vk.api.sdk.objects.groups.LongPollServer;
import com.vk.api.sdk.objects.groups.responses.GetLongPollServerResponse;
import org.apache.http.ConnectionClosedException;
//import org.apache.http.NoHttpResponseException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.Executors;
import java.util.function.Predicate;

abstract class LongPollApi extends EventsHandler {

    private static final Logger LOG = LoggerFactory.getLogger(LongPollApi.class);

    protected final VkApiClient client;

    private final int waitTime;

    private volatile boolean isRunning = false;
    
    protected final Gson gson = new Gson();

    public volatile Predicate<? super EventsHandler> shouldCont = null;

    protected LongPollApi (VkApiClient client, int waitTime) {
        this.client = client;
        this.waitTime = waitTime;
    }

    private LongPollServer getLongPollServer (ApiRequest<GetLongPollServerResponse> request) {
        try {
            GetLongPollServerResponse resp = request.execute();
            return new LongPollServer()
                .setKey(resp.getKey())
                .setTs(resp.getTs())
                .setServer(resp.getServer());
        } catch (ApiException | ClientException e) {
            return null;
        }
    }

    private void handleUpdates (LongPollServer lpServer) throws ConnectionClosedException
    {
        if (lpServer == null)
            LOG.error("Getting LongPoll server was failed");

        isRunning = true;
        try {
            LOG.info("LongPoll handler started to handle events");
            GetLongPollEventsResponse eventsResponse;
            String timestamp = lpServer.getTs();
            while (isRunning) {
                eventsResponse = client.longPoll()
                        .getEvents(lpServer.getServer(), lpServer.getKey(), timestamp)
                        .waitTime(waitTime)
                        .execute();
                //eventsResponse.getUpdates().forEach(e -> System.out.println (e.toString()));
                eventsResponse.getUpdates().forEach (e -> handleBase ( gson.fromJson (e,    //handleBase defined in EventsHandler
                    ((EventClassTypeWrapper)gson.fromJson(e,EventClassTypeWrapper.class)).getClassType() )  // e is JsonObject
                ) );
                timestamp = eventsResponse.getTs();
                if (shouldCont != null && !shouldCont.test(this)) isRunning = false;
            }
            LOG.info("LongPoll handler stopped to handle events");
        } catch (ApiException | ClientException e) {
            /*
            Actually instead of GetLongPollEventsResponse there might be returned error like:
            {"failed":1,"ts":30} or {"failed":2}, but it directly handled in execute() method.
            There are 2 ways: deserialize manually response from string OR do reconnection in each
            error case. There is second way - keep use typed object and reconnect when any error.
            */
            LOG.error("Getting LongPoll events was failed", e);
            throw new ConnectionClosedException();
        }
        isRunning = false;
    }
    
    protected void run (ApiRequest<GetLongPollServerResponse> request) {  //request is request to get server
                boolean needRepeat;
                do {
                    needRepeat = false;
                    try {
                        if (shouldCont != null && shouldCont.test(this)) {isRunning = false; return;}
                        LongPollServer lpServer = getLongPollServer(request);
                        handleUpdates (lpServer);
                    } catch (ConnectionClosedException /*| NoHttpResponseException*/ e) {    //NoHttpResponseException caught before (?)
                        needRepeat = true;
                    }
                } while (needRepeat);
    }

    protected void runThread (ApiRequest<GetLongPollServerResponse> request) {
        Executors.newSingleThreadExecutor().execute(() -> run(request));
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}
