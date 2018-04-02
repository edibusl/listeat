package com.listeat;

import java.io.IOException;
import java.net.URI;
import java.net.InetSocketAddress;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;

import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.xml.internal.ws.developer.SerializationFeature;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException
    {
        System.out.println("Starting ListEat Server");

        startServer();

        System.out.println("Application started on host " + getBaseURI());

        Thread.currentThread().join();
    }

    /**
     * Start HTTP server serving the JAX-RS application.
     *
     * @return new instance of the lightweight HTTP server
     * @throws IOException
     */
    private static HttpServer startServer() throws IOException
    {
        //Create a new server listening at port 9090
        final HttpServer server = HttpServer.create(new InetSocketAddress(getBaseURI().getPort()), 0);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                server.stop(0);
            }
        }));

        // create a handler wrapping the JAX-RS application
        ResourceConfig rc = new DefaultResourceConfig(
            JacksonJsonProvider.class
        );
        rc.add(new JaxRsApplication());
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(rc, HttpHandler.class);

        // map JAX-RS handler to the server root
        server.createContext(getBaseURI().getPath(), handler);

        // start the server
        server.start();

        return server;
    }

    /**
     * Create the base URL for the HTTP server
     *
     * @return base {@link URI}.
     */
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://0.0.0.0/").port(9090).build();
    }
}
