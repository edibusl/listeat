package com.listeat;

import java.io.IOException;
import java.net.URI;
import java.net.InetSocketAddress;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;

import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;


public class Main {
    private static int LISTENING_PORT = 9090;

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
        //Create a new server listening on port 9090
        final HttpServer server = HttpServer.create(new InetSocketAddress(getBaseURI().getPort()), 0);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                server.stop(0);
            }
        }));

        //Create a ResourceConfig class to represent our JaxRs application and add Jackson JSON parser to it
        //to make it serialize/deserialize all requests and responses using this provider
        ResourceConfig rc = new DefaultResourceConfig(
            JacksonJsonProvider.class
        );
        //Add our "JaxRsApplication" class that holds all resource classes
        rc.add(new JaxRsApplication());

        //Map JAX-RS handler to the server root
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(rc, HttpHandler.class);
        server.createContext(getBaseURI().getPath(), handler);

        //Start the server
        server.start();

        return server;
    }

    /**
     * Create the base URL for the HTTP server
     *
     * @return base {@link URI}.
     */
    private static URI getBaseURI() {
        //Listen on 0.0.0.0 in order to accept connection from inside (localhost) and from outside (network)
        return UriBuilder.fromUri("http://0.0.0.0/").port(LISTENING_PORT).build();
    }
}
