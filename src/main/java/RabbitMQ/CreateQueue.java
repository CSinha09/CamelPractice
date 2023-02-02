package RabbitMQ;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CreateQueue {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection("amqp://guest:guest@localhost:5672/");
        Channel channel = connection.createChannel();
        channel.queueDeclare("queue-1", true, false, false, null);
        channel.queueDeclare("queue-2", true, false, false, null);
        channel.queueDeclare("queue-3", true, false, false, null);
        channel.close();
        connection.close();
    }

}
