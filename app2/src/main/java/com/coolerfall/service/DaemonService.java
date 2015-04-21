package com.coolerfall.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.coolerfall.daemon.Daemon;

public class DaemonService extends Service {
    static int k;



    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("DaemonService", "onCreate");
        Daemon.run(this, DaemonService.class, Daemon.INTERVAL_ONE_MINUTE * 2);
    }

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("DaemonService", "onStartCommand");
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    k++;
                    Log.e("k", k + "");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (k == 100) {
                        stopSelf();
                        Daemon.stopService(DaemonService.this);
                    }
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("DaemonService", "onDestroy");
    }
}
