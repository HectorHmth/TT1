package mx.ipn.escom.mps.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.ipn.escom.mps.bs.LocalesBs;
import mx.ipn.escom.mps.entities.Local;

@Path("locales")
@Component
public class LocalesService {
	
	@Autowired
	private LocalesBs localesBs;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response get(){
		try {
			List<Local> locales = localesBs.getLocalesPublicados();
			return Response.status(200).entity(locales).build();
		}catch(Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("local/{nombreLocal}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getLocal(@PathParam("nombreLocal")String nombreLocal) {
		System.out.println("-->Se buscará a " + nombreLocal);
		try {
			Local local = localesBs.getLocalByName(nombreLocal);
			return Response.status(200).entity(local).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("901").build();
		}
	}
	
	//TODO:GET localById
	@GET
	@Path("findById/{idLocal}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocal(@PathParam("idLocal")Integer idLocal) {
		try {
			Local local = localesBs.getLocalById(idLocal);
			return Response.status(200).entity(local).build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("901").build();
		}
	}
}
