package com.williamhao.mqttclient.IM;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.williamhao.mqttclient.DuFrame.utils.LogUtils;

/**
 * Created by WilliamHao on 15/9/18.
 */
public class IMService extends Service{

    private IMBinder mIBinder;

    @Override
    public void onCreate(){
        super.onCreate();
        LogUtils.d("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        LogUtils.d("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent){
        return mIBinder;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        LogUtils.d("onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent){
        LogUtils.d("onUnbind");
        return super.onUnbind(intent);
    }

    public class IMBinder extends Binder {

    }
}
