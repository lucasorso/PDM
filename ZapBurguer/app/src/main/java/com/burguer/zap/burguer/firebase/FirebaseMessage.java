package com.burguer.zap.burguer.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by LucasOrso on 10/08/17.
 */

public class FirebaseMessage extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMessage";

    @Override
    public void onMessageReceived(RemoteMessage aRemoteMessage) {
        super.onMessageReceived(aRemoteMessage);
        Log.i(TAG, "onMessageReceived: " + aRemoteMessage.getNotification().getBody());
    }
}
