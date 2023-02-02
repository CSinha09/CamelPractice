package RabbitMQ;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CreateExchange {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection("amqp://guest:guest@localhost:5672/");
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("direct-exchange-1", BuiltinExchangeType.DIRECT, true);
        channel.exchangeDeclare("direct-exchange-2", BuiltinExchangeType.DIRECT, true);
        channel.exchangeDeclare("direct-exchange-3", BuiltinExchangeType.DIRECT, true);
        channel.close();
        connection.close();
    }

}
