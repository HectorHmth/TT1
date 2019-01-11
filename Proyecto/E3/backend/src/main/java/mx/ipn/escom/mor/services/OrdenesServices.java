package mx.ipn.escom.mor.services;

import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beust.jcommander.internal.Nullable;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

import mx.ipn.escom.entities.PostResponse;
import mx.ipn.escom.mor.bs.OrdenesBs;
import mx.ipn.escom.mor.entities.Orden;
import mx.ipn.escom.mor.entities.OrdenProducto;
import mx.ipn.escom.mor.exception.ProductoAgotadoException;
import mx.ipn.escom.mor.exception.ProductoExistenEnCarritoException;
import mx.ipn.escom.mps.entities.Local;
import mx.ipn.escom.utils.FireBaseManager;

@Path("ordenes")
@Component
public class OrdenesServices {
	
	@Autowired
	private OrdenesBs ordenesBs;
	
	
	//TODO: Verficar los metodos de disponibilidad de productos
	
	
	/**
	 * Agregar un producto a una orden y actualiza la disponibilidad del producto
	 * */
	@POST
	@Path("agregarProducto")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response agregarProducto(@FormParam("idLocal") Integer idLocal
									,@FormParam("idProducto") Integer idProducto
									,@FormParam("nuCantidad") Integer nuCantidad
									,@FormParam("idCuenta") Integer idCuenta
									,@FormParam("comentario") String comentario
									,@Nullable @FormParam("ui")Integer ui) {
		PostResponse postResponse = new PostResponse();
		try {
			Orden orden = ordenesBs.agregarProducto(idLocal, idProducto, nuCantidad, idCuenta,comentario,ui);
			if(orden != null) {
				postResponse.setId(1000);
				postResponse.setResultado("PRODUCTO_AGREGADO_EXITOSAMENTE");
				System.out.println(postResponse.getId() + "-" + postResponse.getResultado());
			}else {
				postResponse.setId(1003);
				postResponse.setResultado("PRODUCTO_NO_AGREGADO");
			}	
		}catch(ProductoAgotadoException e) {
			e.printStackTrace();
			postResponse.setId(1002);
			postResponse.setResultado("PRODUCTO_AGOTADO");
		}catch(ProductoExistenEnCarritoException e) {
			e.printStackTrace();
			postResponse.setId(1001);
			postResponse.setResultado("PRODUCTO_EXISTE_EN_LA_ORDEN");
		}catch(Exception e) {
			e.printStackTrace();
			postResponse.setId(5000);
			postResponse.setResultado("Error General");
			return Response.status(500).entity(postResponse).build();
		}
		return Response.status(200).entity(postResponse).build();
	}
	
	@POST
	@Path("deleteProductoDeOrden/{idOrden}/{idLocal}/{idProductoLocal}/{idCuenta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProductoDeOrden(@PathParam("idOrden") Integer idOrden
											,@PathParam("idLocal") Integer idLocal
											,@PathParam("idProductoLocal") Integer idProductoLocal
											,@PathParam("idCuenta") Integer idCuenta) {
		PostResponse postResponse = new PostResponse();
		try {
			Orden orden = ordenesBs.deleteProductoDeOrden(idOrden,idLocal,idProductoLocal,idCuenta);
			if(orden != null) {
				postResponse.setId(1006);
				postResponse.setResultado("PRODUCTO_ELIMINADO_CORRECTAMENTE");
				return Response.status(200).entity(postResponse).build();
			}
			postResponse.setId(1007);
			postResponse.setResultado("NO_SE_PUEDE_ELIMINAR_PRODUCTO");
			return Response.status(200).entity(postResponse).build();
		}catch(ProductoAgotadoException | NoResultException e) {
			e.printStackTrace();
			postResponse.setId(1007);
			postResponse.setResultado("NO_SE_PUEDE_ELIMINAR_PRODUCTO");
			return Response.status(200).entity(postResponse).build();
		}catch(Exception e) {
			e.printStackTrace();
			postResponse.setId(-1);
			postResponse.setResultado("ERROR_FATAL");
			return Response.status(500).entity(postResponse).build();
		}
	}
	
	@POST
	@Path("finalizarCompra/{idOrden}/{idCuenta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response finalizarCompra(@PathParam("idOrden") Integer idOrden
									,@PathParam("idCuenta") Integer idCuenta) {
		PostResponse postResponse = new PostResponse();
		try {
			Orden orden = ordenesBs.finalizarCompra(idOrden,idCuenta);
			if(orden != null) {
				postResponse.setId(1009);
				postResponse.setResultado("ORDEN_ENVIADA");
				return Response.status(200).entity(postResponse).build();
			}
			postResponse.setId(1010);
			postResponse.setResultado("ERROR_FINALIZAR_ORDEN");
			return Response.status(200).entity(postResponse).build();
		}catch(Exception e) {
			e.printStackTrace();
			postResponse.setId(1010);
			postResponse.setResultado("ERROR_FINALIZAR_ORDEN");
			return Response.status(200).entity(postResponse).build();
		}
	}
	
	
	@GET
	@Path("getAll/{idCuenta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrdenes(@PathParam("idCuenta") Integer idCuenta) {
		try {
			List<Orden> ordenes = ordenesBs.getAllOrdenes(idCuenta);
			return Response.status(200).entity(ordenes).build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(false).build();
		}
	}
	
	@GET
	@Path("getAllOrdenesProducto/{idCuenta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrdenesProducto(@PathParam("idCuenta") Integer idCuenta) {
		try {
			List<OrdenProducto> ordenProducto = ordenesBs.getAllOrdenesProducto(idCuenta);
			if(ordenProducto != null) {
				return Response.status(200).entity(ordenProducto).build();
			}else {
				return Response.status(200).entity(false).build();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(false).build();
		}
	}
	

	@GET
	@Path("getLocalOfOrden/{idCuenta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocalOfOrdenSinConfirmar(@PathParam("idCuenta") Integer idCuenta) {
		try {
			Local local = ordenesBs.getLocalOfOrdenSinConfirmar(idCuenta);
			return Response.status(200).entity(local).build();
		}catch(NoResultException e) {
			e.printStackTrace();
			return Response.status(500).entity(false).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(false).build();
		}
	}
	
	
	@GET
	@Path("getOrdenProductosSinConfirmar/{idCuenta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrdenProductosSinConfirmar(@PathParam("idCuenta") Integer idCuenta){
		try {
			List<OrdenProducto> ordenesProducto = ordenesBs.getOrdenProductosSinConfirmar(idCuenta);
			if(ordenesProducto != null)
				return Response.status(200).entity(ordenesProducto).build();
			else 
				return Response.status(200).entity(false).build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(false).build();
		}
	}

	
	@GET
	@Path("cambiarEstado/{idOrden}/{idEstado}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cambiarEstadoOrden(@PathParam("idOrden") Integer idOrden,
										@PathParam("idEstado") Integer idEstado) {
		FirebaseApp app = FireBaseManager.getInstance();
		FirebaseMessaging mensajes = FirebaseMessaging.getInstance();
		String TOKEN = "";
		try {
		Message message = Message.builder()
				.putData("NUEVO_ESTADO", "EN_PROCESO")
				.setToken(TOKEN)
				.build();
				String response = mensajes.send(message);
				return Response.status(200).entity(response).build();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(FireBaseManager.getInstance());
		return Response.status(200).entity("Ok").build();
	}
	
}
