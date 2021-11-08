package com.mycompany.app;

import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.callback.*;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.events.longpoll.GroupLongPollApi;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import java.lang.InterruptedException;
import java.util.Random;

class MyHandler extends GroupLongPollApi
{
    protected Random rand = new Random();
    public MyHandler (VkApiClient client, GroupActor actor)
        { super (client, actor, 25); }
    @Override
    protected void handle (MessageNew event)
    {
        Message msg = event.getObject().getMessage();
        System.out.println (msg.getText());
        if (!msg.getText().isEmpty()) try {
            getClient().messages().send(getActor()).randomId(rand.nextInt())
                .peerId(msg.getPeerId()).message(msg.getText()).execute();
        } catch (ApiException | ClientException e) {
            System.out.println ("SEND ERROR");
        }
    }
}

public class App
{
    private static final String token = "*** TOKEN ***";
    private static final int group_id = *** GROUP_ID ***;
    public static void main (String[] args) throws ClientException, ApiException, InterruptedException
    {
        MyHandler myhnd = new MyHandler (
            new VkApiClient (HttpTransportClient.getInstance()),
            new GroupActor (group_id, token)
        );
        //System.out.println ("BEGIN");
        //myhnd.run();
        myhnd.runThread();
        System.out.println ("READY");
    }
}
