package RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CreateBindings {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection("amqp://guest:guest@localhost:5672/");
        Channel channel = connection.createChannel();
        channel.queueBind("queue-1", "direct-exchange-1", "routingkey1");
        channel.queueBind("queue-2", "direct-exchange-2", "routingkey2");
        channel.queueBind("queue-3", "direct-exchange-3", "routingkey3");
        channel.close();
        connection.close();
    }

}
