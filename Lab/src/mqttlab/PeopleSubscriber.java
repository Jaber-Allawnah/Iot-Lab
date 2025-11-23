package mqttlab;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PeopleSubscriber {

    public static void main(String[] args) throws Exception {

        MqttClient client = new MqttClient("tcp://localhost:1883", "people-sub");

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {}

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                System.out.println("People received: " + new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(org.eclipse.paho.client.mqttv3.IMqttDeliveryToken token) {}
        });

        client.connect();
        client.subscribe("sensors/room1/people");

        System.out.println("People subscriber waiting...");
    }
}
