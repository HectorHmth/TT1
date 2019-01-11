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

import mx.ipn.escom.mps.bs.ProductosBs;
import mx.ipn.escom.mps.entities.ProductoLocal;

@Path("productos")
@Component
public class ProductosService {
	
	@Autowired
	private ProductosBs productosBs;
	
	
	@GET
	@Path("/productos/{nombreLocal}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getProductosByNombreLocal(@PathParam("nombreLocal") String nombreLocal) {
		try {
			List<ProductoLocal> productosEnLocal = productosBs.getProductosByNombreLocal(nombreLocal);
			return Response.status(200).entity(productosEnLocal).build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(200).entity("Hola").build();
		}
	}
	
	@GET
	@Path("/paquetes/{nombreLocal}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getPaquetesByNombreLocal(@PathParam("nombreLocal") String nombreLocal) {
		try {
			List<ProductoLocal> productosEnLocal = productosBs.getPaquetesByNombreLocal(nombreLocal);
			return Response.status(200).entity(productosEnLocal).build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(200).entity(e).build();
		}
	}
	
	@GET
	@Path("/producto/{idLocal}/{idProducto}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getProductoLocalById(@PathParam("idLocal") Integer idLocal, @PathParam("idProducto") Integer idProducto) {
		try {
			System.out.println("--->Entre");
			ProductoLocal productoLocal = productosBs.getProductoLocalById(idLocal, idProducto);
			return Response.status(200).entity(productoLocal).build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(e).build();
		}
	}
	
	
	/**
	 * Método que verifica la disponibilidad de un producto
	 * @param idLocal 		Es el identificador del local en el que se encuentra el producto para verificar su disponbonibilidad
	 * @param idProducto 	Es el identificador del producto del que se quiere conocer la disponibilidad
	 * @param nuCount 		Es el la cantidad del producto con la que se verifica la disponbilidad de un producto 
	 * */
	@GET
	@Path("/disponibilidad/{idLocal}/{idProducto}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response verifyDisponibilidad(@PathParam("idLocal") Integer idLocal, @PathParam("idProducto") Integer idProducto, @QueryParam("nuCount") Integer nuCount) {
		try {
			Boolean disponible = productosBs.isProductoDisponible(idLocal, idProducto,nuCount);
			return Response.status(200).entity(disponible).build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(200).entity(false).build();
		}
		
	}

}
