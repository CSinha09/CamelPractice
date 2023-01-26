package helloDoc;

import org.apache.camel.builder.RouteBuilder;

public class TestClassRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        System.out.println("hello World using camel");
    }
}
