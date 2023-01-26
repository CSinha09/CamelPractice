package helloDoc;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class TestClass {

    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new TestClassRoutes());
        context.start();
    }

}
