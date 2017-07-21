import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Scanner;

/**
 * Created by Himasha on 7/22/2017.
 */
public class Main {

    private static final String broker = "tcp://0.0.0.0:1883";
    public static final String userName = "admin";
    public static final String password = "admin";

    public static void main(String[] args) throws MqttException {

        Sub sub;
        Pub pub;

        Scanner sc = new Scanner(System.in);
        System.out.println("Subscribe or listning? ");
        String type = sc.nextLine();
        System.out.println("Client ID?");
        String clientId = sc.nextLine();
        System.out.println("topic?");
        String topic = sc.nextLine();

        if(type.equals("SUBSCRIBE")){

            try {
                sub = new Sub(true,userName,password,clientId,broker,topic);

            } catch (MqttException e) {
                e.printStackTrace();
            }


        }
        else if(type.equals("PUBLISH")){
            System.out.println("Message ?");
            String message = sc.nextLine();
            pub = new Pub(true,userName,password,clientId,broker,topic);
            pub.sendMessage(topic,message);
            pub.close();
        }

    }

}
