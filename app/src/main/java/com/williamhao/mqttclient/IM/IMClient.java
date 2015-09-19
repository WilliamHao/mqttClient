package com.williamhao.mqttclient.IM;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.williamhao.mqttclient.DuFrame.utils.LogUtils;

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
            LogUtils.d("---disconnect---");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            imBinder = (IMService.IMBinder) service;
            LogUtils.d("---connected---");
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


    public void bindService(){
        if(null != mContext) {
            Intent intent = new Intent(mContext, IMService.class);
            //mContext.startService(intent);
            mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    }

    public void unbindService(){
        if(null != mContext){
            mContext.unbindService(connection);
        }
    }

    public void setListener(MqttListener listener){
        if(null != imBinder){
            imBinder.setListener(listener);
        }
    }
}
