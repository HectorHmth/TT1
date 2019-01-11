package mx.ipn.escom.prueba.coffeeapp.entities;

import java.io.Serializable;

public class ProductoLocal implements Serializable {
    private Integer idLocal;
    private Integer idProducto;
    private Double precioVenta;
    private Producto producto;
    private EstadoProductoLocal estadoProductoLocal;
    private String notaAlCocionero;
    private Integer cantidad;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getNotaAlCocionero() {
        return notaAlCocionero;
    }

    public void setNotaAlCocionero(String notaAlCocionero) {
        this.notaAlCocionero = notaAlCocionero;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public EstadoProductoLocal getEstadoProductoLocal() {
        return estadoProductoLocal;
    }

    public void setEstadoProductoLocal(EstadoProductoLocal estadoProductoLocal) {
        this.estadoProductoLocal = estadoProductoLocal;
    }

    public String getPrecioVentaString(){
        return "$ " + precioVenta;
    }
}
