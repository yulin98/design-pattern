package com.example.designpattern.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Order(value = -1)
@Async
public class MqttStartService implements CommandLineRunner {

    @Value("${spring.mqtt.url}")
    private String url;

    @Value("${spring.mqtt.completionTimeout}")
    private int completionTimeout;

    @Autowired
    private PushCallback pushCallback;

    @Value("${spring.mqtt.default.topic}")
    private String topics;

    private MqttClient client = null;

    private MqttConnectOptions mqttConnectOptions;

    private Logger logger = LoggerFactory.getLogger(MqttStartService.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("开始循环连接");
        /*for (int i = 1000001; i <= 1100000; i++) {
            Thread.sleep(1000);
            logger.info("第" + i + "连接");
            start(String.valueOf(i));
        }*/


        start(String.valueOf(1000001));

    }

    public void start(String username) throws MqttException {
        //创建客户端连接
        this.client = new MqttClient(url, getClientId(), new MemoryPersistence());
        //设置Callback
        this.client.setCallback(pushCallback);
        //设置客户端连接
        this.client.connect(getMqttConnectOptions(username));
        boolean connected = this.client.isConnected();
        String flag = connected ? "成功" : "失败";
        logger.info("连接状态为：" + flag);
        //创建消息
        String message = "{'data':1}";
        publishMessage(topics,message,2);
    }

    //	对mqttConnectOptions对象的常规设置
    public MqttConnectOptions getMqttConnectOptions(String username) {
        this.mqttConnectOptions = new MqttConnectOptions();
        this.mqttConnectOptions.setCleanSession(true);
        this.mqttConnectOptions.setUserName(username);
        this.mqttConnectOptions.setServerURIs(new String[]{url});
        this.mqttConnectOptions.setConnectionTimeout(completionTimeout);
        this.mqttConnectOptions.setKeepAliveInterval(2000);
        return mqttConnectOptions;
    }

    //	随机生成唯一client.id方法
    public String getClientId() {
        String nums = "design-pattern";
        String substring = UUID.randomUUID().toString().substring(0, 3);
        nums = nums + substring;
        return nums;
    }

    //	发布消息
    public void publishMessage(String pubTopic,String message,int qos) {
        if(null != client&& client.isConnected()){
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(qos);
            mqttMessage.setPayload(message.getBytes());
            MqttTopic topic = client.getTopic(pubTopic);

            if(null != topic) {
                try {
                    MqttDeliveryToken publish = topic.publish(mqttMessage);
                    if(!publish.isComplete()) {
                        logger.info("消息发布成功");
                    }
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
