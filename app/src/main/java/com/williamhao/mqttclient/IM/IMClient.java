package com.williamhao.mqttclient.IM;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by WilliamHao on 15/9/18.
 */
public class IMClient {

    private IMService.IMBinder imBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            imBinder = (IMService.IMBinder) service;
        }
    };
}
