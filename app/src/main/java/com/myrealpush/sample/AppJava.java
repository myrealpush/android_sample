package com.myrealpush.sample;

import android.app.Application;
import android.util.Log;

import com.myrealpush.sdk.MyRealPush;
import com.myrealpush.sdk.MyRealPushNotificationOptions;
import com.myrealpush.sdk.core.MyRealPushNotificationHandler;
import com.myrealpush.sdk.model.MyRealPushAdditionalData;
import com.myrealpush.sdk.model.MyRealPushMessage;
import com.myrealpush.sdk.model.MyRealPushNotificationActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AppJava extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MyRealPushNotificationHandler notificationHandler = new MyRealPushNotificationHandler() {

            //override it if you want to handle open notification event
            @Override
            public void onOpenNotification(@NotNull MyRealPushMessage notification, List<MyRealPushAdditionalData> additionalDataList) {
                super.onOpenNotification(notification, additionalDataList);
                Log.e("Test Application", "onOpenNotification, notification=" + notification);
            }

            //override it if you want to handle action event
            @Override
            public void onAction(@NotNull MyRealPushNotificationActionButton action,
                                 @NotNull MyRealPushMessage notification,
                                 List<MyRealPushAdditionalData> additionalDataList) {
                super.onAction(action, notification, additionalDataList);
                Log.e("Test Application", "onAction, action=" + action + ", notification=" + notification);
            }

            //override it if you want to handle message received event
            @Override
            public void onMessageReceived(@NotNull MyRealPushMessage notification,
                                          List<MyRealPushAdditionalData> additionalDataList) {
                super.onMessageReceived(notification, additionalDataList);
                Log.e("Test Application", "onMessageReceived, notification=" + notification);
            }

            //override it if you want to handle firebase token update event
            @Override
            public void onPushTokenUpdated(@NotNull String token) {
                super.onPushTokenUpdated(token);
                Log.e("Test Application", "onPushTokenUpdated, token=" + token);
            }
        };

        //create options
        MyRealPushNotificationOptions options = new MyRealPushNotificationOptions.Builder()
                .setDebug(true)
                .setHideAllNotificationOnStartApp(true)
                .setProcessAndShowNotification(false)//true (by default) - show notifications using MyRealPush SDK,
                // false - only onMessageReceived from notificationHandler will be called
                .build();

        //init MyRealPush SDK
        MyRealPush.init(this, "YOUR_MYREALPUSH_APP_ID", notificationHandler, options);
    }
}
