package com.williamhao.mqttclient.IM;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.williamhao.mqttclient.DuFrame.utils.LogUtils;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


/**
 * Created by WilliamHao on 15/9/18.
 */
public class IMService extends Service {

    private static final String broker = "tcp://120.25.64.112:1883";
    private static final int qos = 1;
    private static final int keepAlive = 5;
    private IMBinder mIBinder = new IMBinder();
    private boolean isConnect = false;
    private MqttListener mqttListener;
    private MemoryPersistence persistence = null;
    private MqttClient client = null;
    private MqttConnectOptions connOpts = null;
    private Handler mHandler = new Handler() {
        public void handlerMessage(Message msg) {

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.d("service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        LogUtils.d("service onBind");
        connect();
        return mIBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disConnect();
        LogUtils.d("service onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.d("service onUnbind");
        disConnect();
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        LogUtils.d("service onRebind");
        super.onRebind(intent);
    }

    public synchronized void connect() {
        //TODO 先检查网络状态
        LogUtils.d(isConnect+"");
        if (!isConnect) {

            try {
                persistence = new MemoryPersistence();
                client = new MqttClient(broker, "080c0fe219", persistence);
                connOpts = new MqttConnectOptions();

                connOpts.setCleanSession(true);
                connOpts.setConnectionTimeout(10);
                connOpts.setKeepAliveInterval(5);

                client.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable throwable) {
                        throwable.printStackTrace();
                        LogUtils.d("MQTT Connection lost, reconnecting...");
                        isConnect = false;
                        connect();
                    }

                    @Override
                    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                        if (null != mqttListener) {
                            mqttListener.onReceived(s, mqttMessage);
                        }
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                        if (null != mqttListener) {
                            mqttListener.onPublished(iMqttDeliveryToken);
                        }
                    }
                });
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            LogUtils.d("MQTT Connecting...");
                            client.connect(connOpts);
                            LogUtils.d("MQTT Connected");
                            isConnect = true;
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    private void disConnect(){
        LogUtils.d(">>"+(null == client)+">>>"+isConnect);
        if(null != client && isConnect){
            try {
                client.disconnect();
                isConnect = false;
                LogUtils.d("MQTT disconnect");
            }catch(MqttException e){
                e.printStackTrace();
            }
        }
    }

    public class IMBinder extends Binder {

        public void setListener(MqttListener listener) {
            mqttListener = listener;
        }

        public void dis(){
            disConnect();
        }

        public void con(){
            connect();
        }

    }
}
