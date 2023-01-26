package CopyFiles;

import helloDoc.TestClassRoutes;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopy {

    public static void main(String[] args) throws Exception {

        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("file:input_folder?noop=true")
                        .to("file:output_folder");
            }

        });

        context.start(); // we can run this in a infinite loop to check the actual process

    }

}
