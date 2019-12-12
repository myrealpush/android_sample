package com.myrealpush.sample

import android.app.Application
import android.util.Log
import com.myrealpush.sdk.MyRealPush
import com.myrealpush.sdk.MyRealPushNotificationOptions
import com.myrealpush.sdk.core.MyRealPushNotificationHandler
import com.myrealpush.sdk.model.MyRealPushAdditionalData
import com.myrealpush.sdk.model.MyRealPushMessage
import com.myrealpush.sdk.model.MyRealPushNotificationActionButton

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val appId = "YOUR_MYREALPUSH_APP_ID"
        val notificationHandler = object : MyRealPushNotificationHandler() {

            //override it if you want to handle message received event
            override fun onMessageReceived(
                notification: MyRealPushMessage?,
                additionalDataList: List<MyRealPushAdditionalData>?
            ) {
                super.onMessageReceived(notification, additionalDataList)
                Log.e("Test Application", "onMessageReceived, notification=$notification")
            }

            //override it if you want to handle action event
            override fun onAction(
                action: MyRealPushNotificationActionButton,
                notification: MyRealPushMessage, additionalDataList: List<MyRealPushAdditionalData>?
            ) {
                super.onAction(action, notification, additionalDataList)
                Log.e("Test Application", "onAction, action=$action, notification=$notification")
            }

            //override it if you want to handle open notification event
            override fun onOpenNotification(
                notification: MyRealPushMessage,
                additionalDataList: List<MyRealPushAdditionalData>?
            ) {
                super.onOpenNotification(notification, additionalDataList)
                Log.e("Test Application", "onOpenNotification, notification=$notification")
            }

            //override it if you want to handle firebase token update event
            override fun onPushTokenUpdated(token: String) {
                super.onPushTokenUpdated(token)
                Log.e("Test Application", "onPushTokenUpdated, token=$token")
            }
        }

        //create options
        val options = MyRealPushNotificationOptions.Builder()
            .setDebug(true) //use it to see debug messages in Logcat
            .setProcessAndShowNotification(true) //true (by default) - show notifications using MyRealPush SDK,
            // false - only onMessageReceived from notificationHandler will be called
            .build()

        //init MyRealPush SDK
        MyRealPush.init(this, appId, notificationHandler, options)

    }
}