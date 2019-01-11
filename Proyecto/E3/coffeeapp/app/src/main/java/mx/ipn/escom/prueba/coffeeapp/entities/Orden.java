package mx.ipn.escom.prueba.coffeeapp.entities;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Orden implements Serializable {
    private Integer id;
    private Integer idCliente;
    private EstadoOrden estadoOrden;
    private FormaPago formaDePago;
    private Date fecha;
    private Date fechaUltimaActualizacion;
    private TipoOrden tipoDeOrden;
    private Integer prioridad;
    private Boolean confirmada;
    private Date horaEntrega;
    private List<ProductoLocal> productosDeLocalEnOrden;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoOrden getTipoDeOrden() {
        return tipoDeOrden;
    }

    public void setTipoDeOrden(TipoOrden tipoDeOrden) {
        this.tipoDeOrden = tipoDeOrden;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public EstadoOrden getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(EstadoOrden estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public FormaPago getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(FormaPago formaDePago) {
        this.formaDePago = formaDePago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Boolean getConfirmada() {
        return confirmada;
    }

    public void setConfirmada(Boolean confirmada) {
        this.confirmada = confirmada;
    }

    public Date getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(Date horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public List<ProductoLocal> getProductosDeLocalEnOrden() {
        return productosDeLocalEnOrden;
    }

    public void setProductosDeLocalEnOrden(List<ProductoLocal> productosDeLocalEnOrden) {
        this.productosDeLocalEnOrden = productosDeLocalEnOrden;
    }
}
