package com.tradestock.cityfalcom.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tradestock.cityfalcom.Activities.HomeActivity;
import com.tradestock.cityfalcom.Activities.SplashScreenActivity;
import com.tradestock.cityfalcom.BuildConfig;
import com.tradestock.cityfalcom.R;

public class PushNotifictaionService extends FirebaseMessagingService {

    String CHANNEL_ID="1234";


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationHelper helper = new NotificationHelper(this);
        helper.createNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

//        sendMyNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());

//        final int NOTIFICATION_COLOR = getResources().getColor(R.color.colorPrimary);
//        Log.d("msg", "onMessageReceived: " + remoteMessage.getData().get("message"));
//
//        NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//
//        //For API 26+ you need to put some additional code like below:
//        NotificationChannel mChannel;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            mChannel = new NotificationChannel(CHANNEL_ID, "Default_channel", NotificationManager.IMPORTANCE_HIGH);
//            mChannel.setLightColor(Color.GRAY);
//            mChannel.enableLights(true);
//            AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//                    .build();
//            mChannel.setSound(soundUri, audioAttributes);
//
//            if (mNotificationManager != null) {
//                mNotificationManager.createNotificationChannel( mChannel );
//
//            }
//        }
//
//        //General code:
//        NotificationCompat.Builder status = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
//        status.setAutoCancel(true)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.drawable.logo)
//                //.setOnlyAlertOnce(true)
//                .setContentTitle(remoteMessage.getNotification().getTitle())
//                .setContentText(remoteMessage.getNotification().getBody())
//                .setVibrate(new long[]{0, 500, 1000})
//                .setDefaults(Notification.DEFAULT_LIGHTS )
//                .setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+ "://" +getPackageName()+"/"+R.raw.dzyn))
//                ;
//
//        mNotificationManager.notify(999, status.build());

//        AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//                .build();
//        Intent intent = new Intent(this, SplashScreenActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//        String channelId = "Default";
//        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, channelId)
//                .setSmallIcon(R.mipmap.ic_launcher_foreground)
//                .setContentTitle(remoteMessage.getNotification().getTitle())
//                .setContentText(remoteMessage.getNotification().getBody())
//                .setAutoCancel(true)
//                .setColor(NOTIFICATION_COLOR)
//                .setSound(NOTIFICATION_SOUND_URI)
//                .setContentIntent(pendingIntent);
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
//            channel.setSound(NOTIFICATION_SOUND_URI, audioAttributes);
//            manager.createNotificationChannel(channel);
//        }
//        manager.notify(0, builder.build());
    }

//    private void sendMyNotification(String message, String title) {
//
//        Intent intent = new Intent(this, SplashScreenActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//
//        Uri soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE  + "://" + this.getPackageName() +"/raw/dzyn");
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "CH_ID")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(title)
//                .setContentText(message)
//                .setAutoCancel(true)
//                .setSound(soundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//
//            if(soundUri != null){
//                // Changing Default mode of notification
//                notificationBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
//                // Creating an Audio Attribute
//                AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                        .setUsage(AudioAttributes.USAGE_ALARM)
//                        .build();
//
//                // Creating Channel
//                NotificationChannel notificationChannel = new NotificationChannel("CH_ID","Testing_Audio",NotificationManager.IMPORTANCE_HIGH);
//                notificationChannel.setSound(soundUri,audioAttributes);
//                mNotificationManager.createNotificationChannel(notificationChannel);
//            }
//        }
//        mNotificationManager.notify(0, notificationBuilder.build());
//    }


    public class NotificationHelper {

        private Context mContext;
        private NotificationManager mNotificationManager;
        private NotificationCompat.Builder mBuilder;
        public static final String NOTIFICATION_CHANNEL_ID = "10001";

        public NotificationHelper(Context context) {
            mContext = context;
        }

        /**
         * Create and push the notification
         */
        public void createNotification(String title, String message)
        {
            /**Creates an explicit intent for an Activity in your app**/
            Intent resultIntent = new Intent(mContext , SplashScreenActivity.class);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                    0 /* Request code */, resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder = new NotificationCompat.Builder(mContext);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(false)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setContentIntent(resultPendingIntent);

            mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
            {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                assert mNotificationManager != null;
                mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
                mNotificationManager.createNotificationChannel(notificationChannel);
            }
            assert mNotificationManager != null;
            mNotificationManager.notify(0 /* Request Code */, mBuilder.build());
        }
    }

}
