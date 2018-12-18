package com.assegd.university;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/university")
public class UniversityRESTWS {


	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHTMLUniversityInfo() {
		return "<html>" + "<title>" + "Profile Information" + "</title>" + "<body><h1>" + "Name - Assegd _University"
				+ "</body><h1>" + "</html>";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getXMLProfileInfo() {
		return "Name - Assegd _University";
	}

	@PUT
	@Path("{studentRollNo}")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProfileInfo(@PathParam("studentRollNo") String studentRollNo) {
		// Code to update employee record using employeeID can be implemented here
		return "Done Successfully";
	}


}
