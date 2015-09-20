package com.williamhao.mqttclient;

import android.os.Bundle;

import com.androidquery.AQuery;
import com.williamhao.mqttclient.DuFrame.ui.BaseActivity;
import com.williamhao.mqttclient.DuFrame.utils.LogUtils;
import com.williamhao.mqttclient.IM.IMClient;


public class MainActivity extends BaseActivity{

    private AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aq = new AQuery(this);
        aq.id(R.id.btn_bindService).clicked(this,"bind");
        aq.id(R.id.btn_unbindService).clicked(this, "unbind");
        aq.id(R.id.btn_second).clicked(this, "aq_second");
        aq.id(R.id.btn_disconnect).clicked(this,"aq_dis");

        IMClient.getInstance().setContext(this);
        LogUtils.d("activity onCreate");
    }

    @Override
    protected void onPause(){
        super.onPause();
        LogUtils.d("activity onPause");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        LogUtils.d("activity onDestroy");
    }

    @Override
    protected void onResume(){
        super.onResume();
        LogUtils.d("activity onResume");
    }

    public void bind(){
        IMClient.getInstance().bindService();
    }

    public void unbind(){
        IMClient.getInstance().unbindService();
    }

    public void aq_second(){
        redirectToActivity(this, SecondActivity.class);
    }

    public void aq_dis(){
        IMClient.getInstance().getImBinder().dis();
    }
}
