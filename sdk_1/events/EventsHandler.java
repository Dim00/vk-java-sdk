package com.vk.api.sdk.events;

import com.vk.api.sdk.objects.callback.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public abstract class EventsHandler {

    private static final String OVERRIDING_ERR = "Method of handling event is not overridden";

    private static final Logger LOG = LoggerFactory.getLogger(EventsHandler.class);

    protected String handle (Confirmation event) {
        LOG.error(OVERRIDING_ERR);
        return null;
    }

//    protected void handle (AudioNew event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (BoardPostNew event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (BoardPostEdit event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (BoardPostRestore event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (BoardPostDelete event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (GroupLeave event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (GroupJoin event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (GroupChangePhoto event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (GroupChangeSettings event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (GroupOfficersEdit event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (LeadFormsNew event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (MarketCommentNew event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (MarketCommentDelete event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (MarketCommentEdit event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (MarketCommentRestore event) {LOG.error(OVERRIDING_ERR);}
    protected void handle (MessageNew event) {LOG.error(OVERRIDING_ERR);}
    protected void handle (MessageReply event) {LOG.error(OVERRIDING_ERR);}
    protected void handle (MessageEdit event) {LOG.error(OVERRIDING_ERR);}
    protected void handle (MessageAllow event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (MessageDeny event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (MessageRead event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (MessageTypingState event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (MessagesEdit event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (PhotoNew event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (PhotoCommentNew event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (PhotoCommentDelete event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (PhotoCommentEdit event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (PhotoCommentRestore event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (PollVoteNew event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (UserBlock event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (UserUnblock event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (VideoNew event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (VideoCommentNew event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (VideoCommentDelete event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (VideoCommentEdit event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (VideoCommentRestore event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (WallPostNew event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (WallReplyNew event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (WallReplyEdit event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (WallReplyDelete event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (WallReplyRestore event) {LOG.error(OVERRIDING_ERR);}
//    protected void handle (WallRepost event) {LOG.error(OVERRIDING_ERR);}

    protected String handleBase (Base event) {
        if (event.getType() == null) {
            LOG.error("Unexpected callback event type received");
            return null;
        }
        switch (event.getType()) {
//            case AUDIO_NEW: handle ((AudioNew) event); break;
//            case BOARD_POST_NEW: handle ((BoardPostNew) event); break;
//            case BOARD_POST_EDIT: handle ((BoardPostEdit) event); break;
//            case BOARD_POST_RESTORE: handle ((BoardPostRestore) event); break;
//            case BOARD_POST_DELETE: handle ((BoardPostDelete) event); break;
            case CONFIRMATION: return handle ((Confirmation) event); //break;
//            case GROUP_LEAVE: handle ((GroupLeave) event); break;
//            case GROUP_JOIN: handle ((GroupJoin) event); break;
//            case GROUP_CHANGE_PHOTO: handle ((GroupChangePhoto) event); break;
//            case GROUP_CHANGE_SETTINGS: handle ((GroupChangeSettings) event); break;
//            case GROUP_OFFICERS_EDIT: handle ((GroupOfficersEdit) event); break;
//            case LEAD_FORMS_NEW: handle ((LeadFormsNew) event); break;
//            case MARKET_COMMENT_NEW: handle ((MarketCommentNew) event); break;
//            case MARKET_COMMENT_DELETE: handle ((MarketCommentDelete) event); break;
//            case MARKET_COMMENT_EDIT: handle ((MarketCommentEdit) event); break;
//            case MARKET_COMMENT_RESTORE: handle ((MarketCommentRestore) event); break;
            case MESSAGE_NEW: handle ((MessageNew) event); break;
            case MESSAGE_REPLY: handle ((MessageReply) event); break;
            case MESSAGE_EDIT: handle ((MessageEdit) event); break;
            case MESSAGE_ALLOW: handle ((MessageAllow) event); break;
//            case MESSAGE_DENY: handle ((MessageDeny) event); break;
//            case MESSAGE_READ: handle ((MessageRead) event); break;
//            case MESSAGE_TYPING_STATE: handle ((MessageTypingState) event); break;
//            case MESSAGES_EDIT: handle ((MessagesEdit) event); break;
//            case PHOTO_NEW: handle ((PhotoNew) event); break;
//            case PHOTO_COMMENT_NEW: handle ((PhotoCommentNew) event); break;
//            case PHOTO_COMMENT_DELETE: handle ((PhotoCommentDelete) event); break;
//            case PHOTO_COMMENT_EDIT: handle ((PhotoCommentEdit) event); break;
//            case PHOTO_COMMENT_RESTORE: handle ((PhotoCommentRestore) event); break;
//            case POLL_VOTE_NEW: handle ((PollVoteNew) event); break;
//            case USER_BLOCK: handle ((UserBlock) event); break;
//            case USER_UNBLOCK: handle ((UserUnblock) event); break;
//            case VIDEO_NEW: handle ((VideoNew) event); break;
//            case VIDEO_COMMENT_NEW: handle ((VideoCommentNew) event); break;
//            case VIDEO_COMMENT_DELETE: handle ((VideoCommentDelete) event); break;
//            case VIDEO_COMMENT_EDIT: handle ((VideoCommentEdit) event); break;
//            case VIDEO_COMMENT_RESTORE: handle ((VideoCommentRestore) event); break;
//            case WALL_POST_NEW: handle ((WallPostNew) event); break;
//            case WALL_REPLY_NEW: handle ((WallReplyNew) event); break;
//            case WALL_REPLY_EDIT: handle ((WallReplyEdit) event); break;
//            case WALL_REPLY_DELETE: handle ((WallReplyDelete) event); break;
//            case WALL_REPLY_RESTORE: handle ((WallReplyRestore) event); break;
//            case WALL_REPOST: handle ((WallRepost) event); break;
            default:
                LOG.error("Unhandling callback event type received");
                return null;
        }
        return "OK";
    }
}
