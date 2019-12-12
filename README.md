# MyRealPush for android

## How to use it?

1. Add MyRealPush SDK repository with your GitHub access token to project level build.gradle, like:
 
```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://raw.githubusercontent.com/evgentset/push_lib/master/releases'
            credentials(HttpHeaderCredentials) {
                name = "Authorization"
                value = "Bearer {PUT YOUR GITHUB ACCESS TOKEN HERE}"
            }
            authentication {
                header(HttpHeaderAuthentication)
            }
        }
    }
}
```
            
2. Add MyRealPush SDK dependency to your app level build.gradle:
```
dependencies {
    ....
    
    //noinspection GradleDependency
    implementation "com.myrealpush:sdk:[1.0.0, 99.99.99]" //to use latest version (you can ignore notification about newer version)
    //or 
    implementation "com.myrealpush:sdk::1.0.0" //for use some version
}
```

3. Init SDK and use it. We recommend to initiate SDK in your Application class.

Kotlin sample:
```
         val appId = "YOUR_MYREALPUSH_APP_ID"
         
         //create NotificationHandler
         val notificationHandler = object : MyRealPushNotificationHandler() {
 
             //override it if you want to handle message received event
             override fun onMessageReceived(
                 notification: MyRealPushMessage?,
                 additionalDataList: List<MyRealPushAdditionalData>?
             ) {
                 super.onMessageReceived(notification, additionalDataList)
                 Log.d("Sample Application", "onMessageReceived, notification=$notification")
             }
 
             //override it if you want to handle action event
             override fun onAction(
                 action: MyRealPushNotificationActionButton,
                 notification: MyRealPushMessage, additionalDataList: List<MyRealPushAdditionalData>?
             ) {
                 super.onAction(action, notification, additionalDataList)
                 Log.d("Sample Application", "onAction, action=$action, notification=$notification")
             }
 
             //override it if you want to handle open notification event
             override fun onOpenNotification(
                 notification: MyRealPushMessage,
                 additionalDataList: List<MyRealPushAdditionalData>?
             ) {
                 super.onOpenNotification(notification, additionalDataList)
                 Log.d("Sample Application", "onOpenNotification, notification=$notification")
             }
 
             //override it if you want to handle firebase token update event
             override fun onPushTokenUpdated(token: String) {
                 super.onPushTokenUpdated(token)
                 Log.d("Sample Application", "onPushTokenUpdated, token=$token")
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
```
Java sample:
```
         //create NotificationHandler
         MyRealPushNotificationHandler notificationHandler = new MyRealPushNotificationHandler() {
 
             //override it if you want to handle open notification event
             @Override
             public void onOpenNotification(@NotNull MyRealPushMessage notification, List<MyRealPushAdditionalData> additionalDataList) {
                 super.onOpenNotification(notification, additionalDataList);
                 Log.e("Sample Application", "onOpenNotification, notification=" + notification);
             }
 
             //override it if you want to handle action event
             @Override
             public void onAction(@NotNull MyRealPushNotificationActionButton action,
                                  @NotNull MyRealPushMessage notification,
                                  List<MyRealPushAdditionalData> additionalDataList) {
                 super.onAction(action, notification, additionalDataList);
                 Log.e("Sample Application", "onAction, action=" + action + ", notification=" + notification);
             }
 
             //override it if you want to handle message received event
             @Override
             public void onMessageReceived(@NotNull MyRealPushMessage notification,
                                           List<MyRealPushAdditionalData> additionalDataList) {
                 super.onMessageReceived(notification, additionalDataList);
                 Log.e("Sample Application", "onMessageReceived, notification=" + notification);
             }
 
             //override it if you want to handle firebase token update event
             @Override
             public void onPushTokenUpdated(@NotNull String token) {
                 super.onPushTokenUpdated(token);
                 Log.e("Sample Application", "onPushTokenUpdated, token=" + token);
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
```

## Pushes customization

To customize pushes override these resources (in res folder of your application):<br /><br />

Drawables<br />
 ic_def_myrealpush_notification.png - to customize status bar notification icon<br /><br />

Strings<br />
Warnings, Google Play services is missing<br />
 mypealpush_gpls_missing_alert_text - alert text, by default is "To receive push notifications please press 'Update' to enable 'Google Play services'."<br />
 mypealpush_gpls_missing_alert_button_update - update button text,  by default is "Update"<br />
 mypealpush_gpls_missing_alert_button_skip - skip button text,  by default is "Skip"<br />
 mypealpush_gpls_missing_alert_button_close - close button text,  by default is "Close"<br />
