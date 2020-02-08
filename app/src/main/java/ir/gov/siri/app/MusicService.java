package ir.gov.siri.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.io.IOException;

import ir.gov.siri.app.Contact.ContactActivity;


public class MusicService extends Service {

    private MediaPlayer player = null;
    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent.getAction()=="ir.gov.siri.app.StopService")
        {
            stopForeground(true);
            stopSelf();
            return START_NOT_STICKY;
        }

        Log.e("MusicService","onStartCommand"+intent.getStringExtra("url"));
        try {
            player = new MediaPlayer();
            player.setDataSource(intent.getStringExtra("url"));
            player.setLooping(true);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Intent notificationIntent = new Intent(this, ContactActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent actionIntent = new Intent(this, MusicService.class);
        actionIntent.setAction("ir.gov.siri.app.StopService");
        PendingIntent actionPendingIntent = PendingIntent.getService(this, 0,
                actionIntent, 0);


        Notification notification = new NotificationCompat.Builder(this,"1")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My Awesome App")
                .setContentText("Doing some work...")
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.loading_battery,"Stop",actionPendingIntent)
                .addAction(R.drawable.loading_battery,"Stop",actionPendingIntent)
                .build();

        startForeground(1337, notification);



        return Service.START_REDELIVER_INTENT;
    }



    @Override
    public void onDestroy() {
        if(player!=null) {
            player.release();
            player = null;
        }
        super.onDestroy();
    }
}
