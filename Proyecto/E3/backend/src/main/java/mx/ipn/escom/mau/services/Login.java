package mx.ipn.escom.mau.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import mx.ipn.escom.mau.bs.LoginBs;
import mx.ipn.escom.mau.entities.Cuenta;

@Path("login")
public class Login {
	
	@Autowired
	private LoginBs loginBs;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
		System.out.println("--->Datos recibidos:" + username + "-" + password);
		try{
			Cuenta cuenta = loginBs.login(username, password);
			return Response.status(200).entity(cuenta).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

}
