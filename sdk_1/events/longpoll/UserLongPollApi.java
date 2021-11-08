package com.vk.api.sdk.events.longpoll;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;

public abstract class UserLongPollApi extends LongPollApi {

    private final UserActor actor;

    private final int groupId;

    protected UserLongPollApi(VkApiClient client, UserActor actor, int groupId, int waitTime) {
        super(client, waitTime);
        this.actor = actor;
        this.groupId = groupId;
    }

    public VkApiClient getClient() {return client;}
    public UserActor getActor() {return actor;}
    public int getGroupId() {return groupId;}

    public void run() {
        super.run (client.groupsLongPoll().getLongPollServer(actor, groupId));
    }

    public void runThread() {
        super.runThread (client.groupsLongPoll().getLongPollServer(actor, groupId));
    }

}
