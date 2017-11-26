package com.bopr.morze;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by bo on 26.11.2017.
 */

public class BootReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = "BootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG, "Boot intent received");
        MainService.start(context);
    }
}
