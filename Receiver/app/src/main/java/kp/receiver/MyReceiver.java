package kp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,
                      "Rozgloszenie odebrane " + intent.getAction() + " " + intent.getStringExtra("text_message"),
                      Toast.LENGTH_SHORT).show();
    }
}
