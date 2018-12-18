package com.assegd.RESTfulUsingJersey;

import java.util.List;
import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource 
{
	AlienRepository repo = new AlienRepository();
	

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Alien> getAliens()
	{
		System.out.println("getAlien called . . .");	
		return repo.getAliens();
	}
	
	/**
	 * 	receives data from client and returns an object for the given data(id)<br>
	 * 
	 * Should import javax.ws.rs.PathParam not other.
	 * @param id
	 * @return
	 */
	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien getAlien(@PathParam("id") int id)
	{
		System.out.println("getAlien called, gives object for a given id: " + id);	
		return repo.getAlien(id);
	}
	
	@POST
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Alien createAlien(Alien a1) 
	{
		System.out.println(a1);
		repo.create(a1);
		return a1;
	}
	
	@PUT
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Alien updateAlien(Alien a1) 
	{
		System.out.println(a1);
		if (repo.getAlien(a1.getId()).getId()==0) {
			repo.create(a1);
		}
		else {
			repo.update(a1);
		}
		return a1;
	}
	
	@DELETE
	@Path("alien/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Alien deleteAlien(@PathParam("id") int id) 
	{
		System.out.println("kills/deletes an alien with an id of "+ id);
		Alien a = repo.getAlien(id);
		
		if(a.getId() != 0)
			repo.delete(id);
		
		return a;
	}
}
