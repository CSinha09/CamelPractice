package CallingMethodsBeans;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class BeanComponent {

    public static void main(String[] args) throws Exception {

        CallingMethod callingMethod = new CallingMethod();
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("callingMethod", callingMethod);

        CamelContext context = new DefaultCamelContext(registry);

        context.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("bean:callingMethod?method=display");
            }

        });

        context.start();

        ProducerTemplate producerTemplate = context.createProducerTemplate();

        producerTemplate.sendBody("direct:start", "Hello from the producer");

    }

}
