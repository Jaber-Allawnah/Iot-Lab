package mqttlab;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class HumiditySubscriber {

    public static void main(String[] args) throws Exception {

        MqttClient client = new MqttClient("tcp://localhost:1883", "humidity-sub");

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {}

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                System.out.println("Humidity received: " + new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(org.eclipse.paho.client.mqttv3.IMqttDeliveryToken token) {}
        });

        client.connect();
        client.subscribe("sensors/room1/humidity");

        System.out.println("Humidity subscriber waiting...");
    }
}
