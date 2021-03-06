package com.server;

import javax.servlet.ServletException;
import com.undertow.standalone.UndertowServer;
import static com.util.cloud.DeploymentConfiguration.getProperty;


public class Server {

	public static void main(String[] args)
			throws ServletException {

		final String  host = getProperty("undertow.host", "0.0.0.0");
		final Integer port = getProperty("undertow.port", 8080);

		final UndertowServer server = new UndertowServer(host, port, "essentialProgramming.jar");
		server.start();

	}
}
