import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by Himasha on 7/22/2017.
 */
public class Pub {
    private MqttClient client;

    public Pub(boolean cleanSession, String userName, String password, String clientId, String broker, String topic) throws MqttException {

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(cleanSession);
        options.setUserName(userName);
        options.setPassword(password.toCharArray());

        MemoryPersistence presistence = new MemoryPersistence();

            client = new MqttClient(broker,clientId,presistence);
            client.connect(options);


    }

    public void sendMessage(String topic, String msg) throws MqttException {
        MqttMessage message = new MqttMessage();
        message.setPayload(msg.getBytes());
        client.publish(topic,message);
    }

    public void close() throws MqttException {
        client.disconnect();
        client.close();

    }
}
