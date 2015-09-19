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
        LogUtils.d("service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        LogUtils.d("service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent){
        LogUtils.d("service onBind");
        return mIBinder;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        LogUtils.d("service onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent){
        LogUtils.d("service onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent){
        LogUtils.d("service onRebind");
        super.onRebind(intent);
    }

    public class IMBinder extends Binder {

    }
}
