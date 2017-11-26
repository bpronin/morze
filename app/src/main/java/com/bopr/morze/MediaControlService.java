package com.bopr.morze;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;

import java.util.List;

import static android.support.v4.media.session.MediaSessionCompat.*;

/**
 * Created by bo on 26.11.2017.
 */
public class MediaControlService extends MediaBrowserServiceCompat {

    private static final String LOG_TAG = "MediaControlService";
    private static final String MY_MEDIA_ROOT_ID = "media_root_id";
    private static final String MY_EMPTY_MEDIA_ROOT_ID = "empty_root_id";

    private MediaSessionCompat mediaSession;

    @Override
    public void onCreate() {
        super.onCreate();
//        makeForeground();

//        mediaSession = new MediaSessionCompat(this, LOG_TAG);
//        mediaSession.setFlags(FLAG_HANDLES_MEDIA_BUTTONS | FLAG_HANDLES_TRANSPORT_CONTROLS);
//        mediaSession.setCallback(new MediaControlSessionCallback());
//        setSessionToken(mediaSession.getSessionToken());

        Log.d(LOG_TAG, "Media session created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "Service destroyed");
    }

    private void makeForeground() {
        Notification notification = new NotificationCompat.Builder(this, null)
                    .setContentTitle("Title")
                    .setTicker("Ticker")
                    .setContentText("Content text")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .build();
        startForeground(101, notification);
    }

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        return null;  //todo: Implement method.
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
        //todo: Implement method.
    }

    public static void start(Context context) {
        context.startService(new Intent(context, MediaControlService.class));
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

    private class MediaControlSessionCallback extends Callback {

        @Override
        public boolean onMediaButtonEvent(Intent event) {
            Log.i(LOG_TAG, event.toString());
            return super.onMediaButtonEvent(event);
        }
    }
}
