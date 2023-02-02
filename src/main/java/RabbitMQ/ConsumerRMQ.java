package RabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;

public class ConsumerRMQ {

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection("amqp://guest:guest@localhost:5672/");
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, msg) -> {
            System.out.println(consumerTag);
            System.out.println(new String(msg.getBody(), "UTF-8"));
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag);
        };

        channel.basicConsume("queue-3", true, deliverCallback, cancelCallback);

    }

}
