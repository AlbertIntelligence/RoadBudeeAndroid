package com.codekl.roadbudee.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by MonsieurPetion on 2017-11-16.
 */

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, MainActivity.class));
    }
}
