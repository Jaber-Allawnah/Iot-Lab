package mqttlab;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;

public class PeoplePublisher {

    public static void main(String[] args) throws Exception {

        MqttClient client = new MqttClient("tcp://localhost:1883", "people-pub");
        client.connect();
        System.out.println("People publisher connected");

        Random rand = new Random();
        int count = 0;

        while (true) {
            count += rand.nextInt(4) - 1;
            if (count < 0) count = 0;

            String payload = String.valueOf(count);

            MqttMessage msg = new MqttMessage(payload.getBytes());
            client.publish("sensors/room1/people", msg);

            System.out.println("Sent people count: " + payload);
            Thread.sleep(3000);
        }
    }
}
