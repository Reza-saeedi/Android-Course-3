package ir.gov.siri.app.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class PowerBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"PowerBroadcastReceiver",Toast.LENGTH_SHORT).show();
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        //Intent intent1 = new Intent(context, MainActivity.class);
        //intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //context.startActivity(intent1);
        abortBroadcast();


    }
}
