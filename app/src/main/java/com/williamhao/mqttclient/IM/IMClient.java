package com.williamhao.mqttclient.IM;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by WilliamHao on 15/9/18.
 */
public class IMClient {

    private IMService.IMBinder imBinder;
    private static IMClient mIMClient;
    private Context mContext;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            imBinder = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            imBinder = (IMService.IMBinder) service;
        }
    };

    public static IMClient getInstance(){
        if(null == mIMClient){
            mIMClient = new IMClient();
        }

        return mIMClient;
    }

    public void setContext(Activity activity){
        mContext = activity;
    }

}
