import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by Himasha on 7/22/2017.
 */
public class Sub implements MqttCallback{

    private MqttClient mqttClient;

    public Sub(boolean cleanSession, String userName, String password, String clientId, String broker, String topic) throws MqttException{

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(cleanSession);
        options.setUserName(userName);
        options.setPassword(password.toCharArray());

        MemoryPersistence presistence = new MemoryPersistence();
        mqttClient = new MqttClient(broker,clientId,presistence);

        mqttClient.setCallback(this);
        mqttClient.connect(options);
        mqttClient.subscribe(topic);


    }

    public void close(){
        try {
            mqttClient.disconnect();
            mqttClient.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println(mqttMessage.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
