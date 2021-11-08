package com.vk.api.sdk.events.longpoll;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;

public abstract class GroupLongPollApi extends LongPollApi {

    private final GroupActor actor;

    protected GroupLongPollApi(VkApiClient client, GroupActor actor, int waitTime) {
        super(client, waitTime);
        this.actor = actor;
    }

    public VkApiClient getClient() {return client;}
    public GroupActor getActor() {return actor;}

    public void run() {
        super.run (client.groupsLongPoll().getLongPollServer(actor, actor.getGroupId()));
    }

    public void runThread() {
        super.runThread (client.groupsLongPoll().getLongPollServer(actor, actor.getGroupId()));
    }

}
