package mx.ipn.escom.prueba.coffeeapp.entities;

public class Producto {
    private Integer id;
    private Integer idEstado;
    private String nombreProducto;
    private Double tmEstimadoPrep;
    private Double nuRating;
    private String descripcion;

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getTmEstimadoPrep() {
        return tmEstimadoPrep;
    }

    public void setTmEstimadoPrep(Double tmEstimadoPrep) {
        this.tmEstimadoPrep = tmEstimadoPrep;
    }

    public Double getNuRating() {
        return nuRating;
    }

    public void setNuRating(Double nuRating) {
        this.nuRating = nuRating;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
