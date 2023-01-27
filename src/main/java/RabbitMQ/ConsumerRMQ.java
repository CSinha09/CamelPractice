package RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.Date;

public class ConsumerRMQ {

    public static void main(String[] args) throws Exception {

        CamelContext context = new DefaultCamelContext();
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection("amqp://guest:guest@localhost:5672/");
        Channel channel = connection.createChannel();
        channel.queueDeclare("testQueue", true, false, false, null);

        context.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("rabbitmq://localhost:5672/amq.direct?routingKey=testQueue&autoDelete=false&username=guest&password=guest")
                        .to("seda:end");
            }

        });

        context.start();

        ConsumerTemplate consumerTemplate = context.createConsumerTemplate();
        String msg = consumerTemplate.receiveBody("seda:end", String.class);
        System.out.println(msg);

    }

}