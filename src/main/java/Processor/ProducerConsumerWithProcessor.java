package Processor;

import org.apache.camel.*;
import org.apache.camel.builder.ProcessClause;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProducerConsumerWithProcessor {

    public static void main(String[] args) throws Exception {

        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                // System.out.println("Hello from the processor");
                                String msg = exchange.getIn().getBody(String.class);
                                msg += " - Processed by processor";
                                exchange.getOut().setBody(msg);
                            }
                        })
                        .to("seda:end");
            }

        });

        context.start();

        ProducerTemplate producerTemplate = context.createProducerTemplate();

        producerTemplate.sendBody("direct:start", "Hello from the producer");

        ConsumerTemplate consumerTemplate = context.createConsumerTemplate();

        String msg = consumerTemplate.receiveBody("seda:end", String.class);

        System.out.println(msg);

    }

}
