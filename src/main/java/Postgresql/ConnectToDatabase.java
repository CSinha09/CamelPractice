package Postgresql;

import CallingMethodsBeans.CallingMethod;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class ConnectToDatabase {

    public static void main(String[] args) throws Exception {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        DataSource dataSource = setupDataSource(url);

        SimpleRegistry registry = new SimpleRegistry();
        registry.put("myDataSource", dataSource);

        CamelContext context = new DefaultCamelContext(registry);

        context.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("jdbc:myDataSource")
                        .bean(new DisplayQueries(), "display");
            }

        });

        context.start();

        ProducerTemplate producerTemplate = context.createProducerTemplate();

        producerTemplate.sendBody("direct:start", "select * from student");

    }

    private static DataSource setupDataSource(String url) {

        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("postgres");
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setPassword("postgres");
        ds.setUrl(url);
        return ds;

    }

}
