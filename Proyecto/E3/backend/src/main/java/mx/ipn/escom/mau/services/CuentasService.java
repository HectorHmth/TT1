package mx.ipn.escom.mau.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.ipn.escom.mau.bs.CuentasBs;
import mx.ipn.escom.mau.entities.Persona;

@Path("cuentas")
@Component
public class CuentasService {
	
	@Autowired
	private CuentasBs cuentasBs;
	
	@GET
	@Path("getPersona/{idCuenta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersona(@PathParam("idCuenta") Integer idCuenta)  {
		try {
			Persona persona  = cuentasBs.getPersona(idCuenta);
			if(persona !=null) {
				return Response.status(200).entity(persona).build();
			}else {
				return Response.status(200).entity(false).build();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(200).entity(false).build();
		}
	}

}
