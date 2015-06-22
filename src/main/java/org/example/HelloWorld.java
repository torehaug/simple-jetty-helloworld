package org.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HelloWorld extends AbstractHandler {

	private String serverVersion;

	public HelloWorld(String serverVersion) {
		this.serverVersion = serverVersion;
	}

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);

		final PrintWriter out = response.getWriter();
		out.println("<h1>Hello World from Jetty</h1>");
		out.println("Server version: " + serverVersion);
	}

	public static void main(String[] args) throws Exception {
		final Server server = new Server(8080);

		server.setHandler(new HelloWorld(Server.getVersion()));
		System.err.println("Jetty server version: " + Server.getVersion());
		server.start();
		server.join();
	}

}
