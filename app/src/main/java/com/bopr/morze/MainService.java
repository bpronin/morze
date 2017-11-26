package com.bopr.morze;


import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;

import java.util.List;

import static android.support.v4.media.session.MediaSessionCompat.Callback;
import static android.support.v4.media.session.MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS;

/**
 * Class MainService
 */
public class MainService extends MediaBrowserServiceCompat {

    private static final String LOG_TAG = "MainService";

    private MediaSessionCompat mediaSession;

    @Override
    public void onCreate() {
        super.onCreate();
        bringForeground();

        mediaSession = new MediaSessionCompat(this, LOG_TAG);
        mediaSession.setFlags(FLAG_HANDLES_MEDIA_BUTTONS);
        mediaSession.setCallback(new MediaControlSessionCallback());
//        setSessionToken(mediaSession.getSessionToken());

        Log.d(LOG_TAG, "Service created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "Service destroyed");
    }

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        return null;
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
    }

    private void bringForeground() {
        Notification notification = new NotificationCompat.Builder(this, "com.bopr.morze")
                .setContentTitle("Title")
                .setTicker("Ticker")
                .setContentText("Content text")
                .setSmallIcon(R.drawable.ic_settings_ethernet)
                .build();
        startForeground(101, notification);
    }

    public static void start(Context context) {
        context.startService(new Intent(context, MainService.class));
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Bitmap icon = Bitmap.createScaledBitmap(
//                    BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_foreground),
//                    128, 128, false);
//
//            Notification notification = new NotificationCompat.Builder(context)
//                    .setContentTitle("Title")
//                    .setTicker("Ticker")
//                    .setContentText("Content text")
//                    .setSmallIcon(R.drawable.ic_launcher_foreground)
//                    .setLargeIcon(icon)
//                    .setOngoing(true)
//                    .build();
//
//            Intent intent = new Intent();
//
//            context.startForegroundService(intent);
//        } else {
//            context.startForeground();
//            throw new RuntimeException("Unsupported OS version");
//        }
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, MainService.class));
    }

    private class MediaControlSessionCallback extends Callback {

        @Override
        public boolean onMediaButtonEvent(Intent event) {
            Log.i(LOG_TAG, event.toString());
            return super.onMediaButtonEvent(event);
        }
    }
}
