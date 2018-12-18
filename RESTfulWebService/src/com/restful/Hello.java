package com.restful;

import javax.servlet.ServletContext;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("Hello")
public class Hello {

	//Example: http://localhost:8080/RESTfulWebService/rest/Hello/sayHello?name=assegd&Card_No=123&Amount=2000000
	@GET
	@Path("/sayHello")
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello(@QueryParam("name") String name, @QueryParam("Card_No") String Card_No, @QueryParam("Amount") int Amount, @Context ServletContext context)
	{
		String retString;
		System.out.println("Name is "+ name);
		System.out.println("Amount : "+ Amount);
		

		if (Amount > 100000) {
			System.out.println("Amount is greater than a lakh");
			retString = "Credit card amount is not approved....";
		}
		else 
		{
		 System.out.println("Amount is less than lakh");
		 retString = "Credit card is approved....";
		}
		
		return "<html>" + "<title>" + "Credit Card authorization" + name + "</title>"
				+ "<body><h1>" + retString + "</body></h1>" + "</html>";
	}
	
	
	/**
	 * receives data(name) from client and returns an plain text for the given name<br>
	 * 
	 * Should import javax.ws.rs.PathParam not other.﻿
	 * @param name
	 * @return
	 */
	@GET
	@Path("welcome/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHtmlWelcome(@PathParam("name") String name)
	{
		return ("Welcome to ABC Bank: " + name);
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hiRestful()
	{
		return "Hi RESTful WS!";
	}
}
