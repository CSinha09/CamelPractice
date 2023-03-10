package RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.Date;

public class ProducerRMQ {

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection("amqp://guest:guest@localhost:5672/");
        Channel channel = connection.createChannel();

        String msg = "msg from direct-exchange-3 to queue-1&3";
        channel.basicPublish("direct-exchange-3", "routingkey3", null, msg.getBytes());

        channel.close();
        connection.close();

    }

}
