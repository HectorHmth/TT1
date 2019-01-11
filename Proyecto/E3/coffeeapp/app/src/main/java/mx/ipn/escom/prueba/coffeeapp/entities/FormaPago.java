package mx.ipn.escom.prueba.coffeeapp.entities;

import java.io.Serializable;

public class FormaPago implements Serializable {
    private Integer id;
    private TipoForma tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoForma getTipo() {
        return tipo;
    }

    public void setTipo(TipoForma tipo) {
        this.tipo = tipo;
    }
}
