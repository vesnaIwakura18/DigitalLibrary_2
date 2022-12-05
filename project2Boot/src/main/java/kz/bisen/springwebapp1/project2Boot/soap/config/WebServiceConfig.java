package kz.bisen.springwebapp1.project2Boot.soap.config;

import kz.bisen.springwebapp1.project2Boot.soap.client.impl.BookServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {
    private final Bus bus;

    @Autowired
    public WebServiceConfig(Bus bus) {
        this.bus = bus;
    }

    @Bean
    public Endpoint bookEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new BookServiceImpl());
        endpoint.publish("/book");

        return endpoint;
    }
}
