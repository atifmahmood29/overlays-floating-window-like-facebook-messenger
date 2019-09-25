package innovativepocket.com.systemalertwindow;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

public class ScreenActionReceiver extends BroadcastReceiver {

    private String TAG = "ScreenActionReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {


        String action = intent.getAction();

        if(Intent.ACTION_SCREEN_ON.equals(action))
        {
            Log.d(TAG, "screen is on...");
            Toast.makeText(context,"screen ON",Toast.LENGTH_LONG);

            //Run the locker
            Intent i = new Intent(context, FloatingWindow.class);
            context.startService(i);

        }

        else if(Intent.ACTION_SCREEN_OFF.equals(action))
        {
            Log.d(TAG, "screen is off...");
            Toast.makeText(context,"screen OFF",Toast.LENGTH_LONG);

        }

        else if(Intent.ACTION_USER_PRESENT.equals(action))
        {
            Log.d(TAG, "screen is unlock...");
            Toast.makeText(context,"screen UNLOCK",Toast.LENGTH_LONG);

        }

        else if(Intent.ACTION_BOOT_COMPLETED.equals(action)){
            Log.d(TAG, "boot completed...");
            Toast.makeText(context,"BOOTED..",Toast.LENGTH_LONG);
        }

    }

    public IntentFilter getFilter(){
        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        return filter;
    }

}