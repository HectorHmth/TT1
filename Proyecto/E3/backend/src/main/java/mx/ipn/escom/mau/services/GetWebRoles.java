package mx.ipn.escom.mau.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.ipn.escom.mau.bs.WebRolesBs;
import mx.ipn.escom.mau.entities.Rol;

@Path("webRoles")
@Component
public class GetWebRoles {
	
	@Autowired
	private WebRolesBs webRolesBs;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response get() {
		try {
			List<Rol> rolesWeb = webRolesBs.getWebRoles();
			return Response.status(200).entity(rolesWeb).build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(e.getMessage()).build();
		}
		
	}

}
