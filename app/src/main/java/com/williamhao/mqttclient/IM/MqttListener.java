package com.williamhao.mqttclient.IM;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by WilliamHao on 15/9/19.
 */
public interface MqttListener {
    void onPublished(IMqttDeliveryToken token);
    void onReceived(String topicName, MqttMessage message);
}
