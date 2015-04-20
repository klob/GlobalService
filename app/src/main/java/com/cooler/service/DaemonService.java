package com.cooler.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.cooler.daemon.Daemon;

public class DaemonService extends Service {
    static int k;

    @Override
    public void onCreate() {
        super.onCreate();
        Daemon.run(this, DaemonService.class, Daemon.INTERVAL_ONE_MINUTE * 2);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /* do something here */
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    k++;
                    Log.e("k", k + "");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (k == 50) {
                        stopSelf();
                      //  break;
                    }
                }
            }
        }.start();
        return START_STICKY;
    }
}
