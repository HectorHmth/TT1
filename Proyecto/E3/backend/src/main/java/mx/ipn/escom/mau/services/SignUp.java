package mx.ipn.escom.mau.services;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import mx.ipn.escom.entities.PostResponse;
import mx.ipn.escom.exception.NombreUsuarioRepetido;
import mx.ipn.escom.mau.bs.SignUpBs;
import mx.ipn.escom.mau.entities.Acceso;
import mx.ipn.escom.mau.entities.Cuenta;
import mx.ipn.escom.mau.entities.Persona;
import mx.ipn.escom.mor.entities.Cliente;

@Path("signup")
public class SignUp {
	
	
	@Autowired
	private SignUpBs signUpBs;
 
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response onSignUp(@FormParam("nombrePersona") String nombrePersona
            ,@FormParam("primerApellido") String primerApellido
            ,@FormParam("segundoApellido") String segundoApellido
            ,@FormParam("correoElectronico") String correo
            ,@FormParam("nombreUsuario") String nombreUsuario
            ,@FormParam("contrasena") String contrasena) {
			PostResponse postResponse = new PostResponse();
			try {
				Persona persona = bindPersona(nombrePersona
			            ,primerApellido
			            ,segundoApellido
			            ,correo
			            ,nombreUsuario
			            ,contrasena);
				Persona aux = signUpBs.registrarPersona(persona);
				if(aux != null) {
					postResponse.setId(801);
					postResponse.setResultado("REGISTRO_EXITOSO");
					return Response.status(200).entity(postResponse).build();
				}else {
					postResponse.setId(802);
					postResponse.setResultado("REGISTRO_ERRONEO");
					return Response.status(200).entity(postResponse).build();
				}
			}catch(NombreUsuarioRepetido e) {
				postResponse.setId(800);
				postResponse.setResultado("CUENTA_EXISTE_EXCEPTION");
				return Response.status(200).entity(postResponse).build();
			}catch(Exception e) {
				e.printStackTrace();
				postResponse.setId(502);
				postResponse.setResultado("OTHER_EXCEPTION");
				return Response.status(200).entity(postResponse).build();
			}
		
	}
	
	
	private Persona bindPersona(String nombrePersona
            ,String primerApellido
            ,String segundoApellido
            ,String correo
            ,String nombreUsuario
            ,String contrasena) {
		
		
		Persona persona = new Persona();
		persona.setNombrePersona(nombrePersona);
		persona.setPrimerApellido(primerApellido);
		persona.setSegundoApellido(segundoApellido);
		persona.setCuenta(new Cuenta());
		
		Cuenta cuenta = persona.getCuenta();
		cuenta.setNombreUsuario(nombreUsuario);
		cuenta.setCorreo(correo);
		cuenta.setContrasena(contrasena);
		cuenta.setFechaCreacion(new Date());
		cuenta.setAcceso(new Acceso());
		
		Acceso acceso = cuenta.getAcceso();
		acceso.setFechaUltimoAcceso(new Date());
		acceso.setSesionActiva(0);
				
		return persona;
	}

}
