package com.vk.api.sdk.objects.callback.messages;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.callback.*;
import java.util.Objects;
import java.lang.reflect.Type;

/**
 * Created by Dim00 on 04.11.21.
 */

// It is used for longpoll API instead of objects.callback.messages.CallbackMessage
// and events.Events.

public class EventClassTypeWrapper
{

  public enum EventClassType
  {

    //@SerializedName("audio_new")
    //AUDIO_NEW(AudioNew.class),

    //@SerializedName("board_post_new")
    //BOARD_POST_NEW(BoardPostNew.class),

    //@SerializedName("board_post_edit")
    //BOARD_POST_EDIT(BoardPostEdit.class),

    //@SerializedName("board_post_restore")
    //BOARD_POST_RESTORE(BoardPostRestore.class),

    //@SerializedName("board_post_delete")
    //BOARD_POST_DELETE(BoardPostDelete.class),

    @SerializedName("confirmation")
    CONFIRMATION(Confirmation.class),

    //@SerializedName("group_leave")
    //GROUP_LEAVE(GroupLeave.class),

    //@SerializedName("group_join")
    //GROUP_JOIN(GroupJoin.class),

    //@SerializedName("group_change_photo")
    //GROUP_CHANGE_PHOTO(GroupChangePhoto.class),

    //@SerializedName("group_change_settings")
    //GROUP_CHANGE_SETTINGS(GroupChangeSettings.class),

    //@SerializedName("group_officers_edit")
    //GROUP_OFFICERS_EDIT(GroupOfficersEdit.class),

    //@SerializedName("lead_forms_new")
    //LEAD_FORMS_NEW(LeadFormsNew.class),

    //@SerializedName("market_comment_new")
    //MARKET_COMMENT_NEW(MarketCommentNew.class),

    //@SerializedName("market_comment_delete")
    //MARKET_COMMENT_DELETE(MarketCommentDelete.class),

    //@SerializedName("market_comment_edit")
    //MARKET_COMMENT_EDIT(MarketCommentEdit.class),

    //@SerializedName("market_comment_restore")
    //MARKET_COMMENT_RESTORE(MarketCommentRestore.class),

    @SerializedName("message_new")
    MESSAGE_NEW(MessageNew.class),

    @SerializedName("message_reply")
    MESSAGE_REPLY(MessageReply.class),

    @SerializedName("message_edit")
    MESSAGE_EDIT(MessageEdit.class),

    @SerializedName("message_allow")
    MESSAGE_ALLOW(MessageAllow.class);//,

    //@SerializedName("message_deny")
    //MESSAGE_DENY(MessageDeny.class),

    //@SerializedName("message_read")
    //MESSAGE_READ(MessageRead.class),

    //@SerializedName("message_typing_state")
    //MESSAGE_TYPING_STATE(MessageTypingState.class),

    //@SerializedName("messages_edit")
    //MESSAGES_EDIT(MessagesEdit.class),

    //@SerializedName("photo_new")
    //PHOTO_NEW(PhotoNew.class),

    //@SerializedName("photo_comment_new")
    //PHOTO_COMMENT_NEW(PhotoCommentNew.class),

    //@SerializedName("photo_comment_delete")
    //PHOTO_COMMENT_DELETE(PhotoCommentDelete.class),

    //@SerializedName("photo_comment_edit")
    //PHOTO_COMMENT_EDIT(PhotoCommentEdit.class),

    //@SerializedName("photo_comment_restore")
    //PHOTO_COMMENT_RESTORE(PhotoCommentRestore.class),

    //@SerializedName("poll_vote_new")
    //POLL_VOTE_NEW(PollVoteNew.class),

    //@SerializedName("user_block")
    //USER_BLOCK(UserBlock.class),

    //@SerializedName("user_unblock")
    //USER_UNBLOCK(UserUnblock.class),

    //@SerializedName("video_new")
    //VIDEO_NEW(VideoNew.class),

    //@SerializedName("video_comment_new")
    //VIDEO_COMMENT_NEW(VideoCommentNew.class),

    //@SerializedName("video_comment_delete")
    //VIDEO_COMMENT_DELETE(VideoCommentDelete.class),

    //@SerializedName("video_comment_edit")
    //VIDEO_COMMENT_EDIT(VideoCommentEdit.class),

    //@SerializedName("video_comment_restore")
    //VIDEO_COMMENT_RESTORE(VideoCommentRestore.class),

    //@SerializedName("wall_post_new")
    //WALL_POST_NEW(WallPostNew.class),

    //@SerializedName("wall_reply_new")
    //WALL_REPLY_NEW(WallReplyNew.class),

    //@SerializedName("wall_reply_edit")
    //WALL_REPLY_EDIT(WallReplyEdit.class),

    //@SerializedName("wall_reply_delete")
    //WALL_REPLY_DELETE(WallReplyDelete.class),

    //@SerializedName("wall_reply_restore")
    //WALL_REPLY_RESTORE(WallReplyRestore.class),

    //@SerializedName("wall_repost")
    //WALL_REPOST(WallRepost.class);

    private final Type type;

    EventClassType (Type type) {
        this.type = type;
    }

    public Type getClassType() {
        return this.type;
    }
  }

    @SerializedName("type")
    private EventClassType type;

    public Type getClassType() {
        if (type == null) return Base.class;  // to prevent errors at this stage. ("Unexpected callback event" will be tested later)
        return type.getClassType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventClassTypeWrapper that = (EventClassTypeWrapper) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventClassTypeWrapper{");
        sb.append("type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
