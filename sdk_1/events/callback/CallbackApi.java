package com.vk.api.sdk.events.callback;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vk.api.sdk.events.EventsHandler;
import com.vk.api.sdk.objects.callback.messages.EventClassTypeWrapper;
import com.vk.api.sdk.objects.callback.Confirmation;
import com.vk.api.sdk.objects.callback.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CallbackApi extends EventsHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CallbackApi.class);

    private final String confirmationCode;

    private final String secretKey;
    
    protected final Gson gson = new Gson();

    private boolean isSecretKeyValid(String receivedKey) {
        if (secretKey == null && receivedKey == null) // secret key was not received and not initialized for API client
            return true;
        if (secretKey == null || receivedKey == null) // secret key was not received but was expected, or vice versa
            return false;
        return secretKey.equals(receivedKey);
    }

    //@Override
    protected String confirmation() {
        return this.confirmationCode;
    }
    
    @Override
    protected String handle (Confirmation event) {
        return confirmation();
    }

    protected CallbackApi(String confirmationCode) {
        this.confirmationCode = confirmationCode;
        this.secretKey = null;
    }

    protected CallbackApi(String confirmationCode, String secretKey) {
        this.confirmationCode = confirmationCode;
        this.secretKey = secretKey;
    }

    public String parse(String str) {
        return parse((JsonObject)gson.fromJson(str, JsonObject.class));   // first to JsonObject, then twice to different classes
    }

    public String parse(JsonObject e) {
        return handleBase ( gson.fromJson (e,
          ((EventClassTypeWrapper)gson.fromJson(e,EventClassTypeWrapper.class)).getClassType() )
        );
    }

    @Override
    public String handleBase (Base event) {
        if (isSecretKeyValid(event.getSecret()))
            return super.handleBase(event);
        LOG.error("Secret key check was failed");
        return "failed";
    }
}