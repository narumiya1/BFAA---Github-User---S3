package com.cryptbrn.gitconnect.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.cryptbrn.gitconnect.R;
import com.cryptbrn.gitconnect.view.MainActivity;


public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context c, Intent intent) {
        Intent intentNotif = new Intent(c, MainActivity.class);
        PendingIntent pending = PendingIntent.getActivity(c, 102, intentNotif, PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d("aSwitch", "aSwitch");

        NotificationManager notifmanager = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder build = new NotificationCompat.Builder(c, "102")
                .setContentIntent(pending)
                .setSmallIcon(R.drawable.ic_notif)
                .setLargeIcon(BitmapFactory.decodeResource(c.getResources(), R.drawable.ic_favorite))
                .setContentTitle(c.getResources().getString(R.string.ContentTittleNotif))
                .setContentText(c.getResources().getString(R.string.SubTextNotif))
                .setAutoCancel(true);

        Notification notif = build.build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notifchannel = new NotificationChannel("102", "AlarmManager Channel", NotificationManager.IMPORTANCE_DEFAULT);
            build.setChannelId("102");
            if (notifmanager != null) {
                notifmanager.createNotificationChannel(notifchannel);
            }
        }
        if (notifmanager != null) {
            notifmanager.notify(2, notif);
        }
    }
}