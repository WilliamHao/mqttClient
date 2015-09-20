package com.williamhao.mqttclient;

import android.os.Bundle;

import com.williamhao.mqttclient.DuFrame.ui.BaseActivity;
import com.williamhao.mqttclient.DuFrame.utils.LogUtils;
import com.williamhao.mqttclient.IM.IMClient;
import com.williamhao.mqttclient.IM.IMService;


public class SecondActivity extends BaseActivity {

    private IMService.IMBinder imBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imBinder = IMClient.getInstance().getImBinder();

        if(null != imBinder){
            imBinder.con();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        LogUtils.d("second destroy");
    }

}
