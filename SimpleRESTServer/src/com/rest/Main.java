package com.rest;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

	public static void main(String[] args) {
		
		int port = 8080;
		Server server = new Server(port);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
        
		// http://localhost:8080/rest
		context.addServlet(new ServletHolder(new AppServlet()), "/rest");

		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { context });
		server.setHandler(handlers);   		

		try {
			server.start();
			System.out.println("Listening port : " + port);
	        
			server.join();
		} catch (Exception e) {
			System.out.println("Error.");
			e.printStackTrace();
		}

	}

}
