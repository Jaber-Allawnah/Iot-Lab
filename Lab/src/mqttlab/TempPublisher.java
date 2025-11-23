package mqttlab;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;

public class TempPublisher {

    public static void main(String[] args) throws Exception {

        MqttClient client = new MqttClient("tcp://localhost:1883", "temp-pub");
        client.connect();
        System.out.println("Temp Publisher connected");

        Random random = new Random();

        while (true) {
            double temp = 20 + random.nextDouble() * 10;
            String payload = String.format("%.2f", temp);

            MqttMessage msg = new MqttMessage(payload.getBytes());
            client.publish("sensors/room1/temp", msg);

            System.out.println("Sent temperature: " + payload);
            Thread.sleep(2000);
        }
    }
}
