package com.williamhao.mqttclient;

import android.os.Bundle;

import com.williamhao.mqttclient.DuFrame.ui.BaseActivity;
import com.williamhao.mqttclient.DuFrame.utils.LogUtils;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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

}
