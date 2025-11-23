package mqttlab;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;

public class HumidityPublisher {

    public static void main(String[] args) throws Exception {

        MqttClient client = new MqttClient("tcp://localhost:1883", "humidity-pub");
        client.connect();
        System.out.println("Humidity publisher connected");

        Random rand = new Random();

        while (true) {
            int humidity = 30 + rand.nextInt(51); // 30–80
            String payload = String.valueOf(humidity);

            MqttMessage msg = new MqttMessage(payload.getBytes());
            client.publish("sensors/room1/humidity", msg);

            System.out.println("Sent humidity: " + payload);
            Thread.sleep(2000);
        }
    }
}
