package mx.ipn.escom.entities;

import java.io.Serializable;

public class PostResponse implements Serializable{

	private Integer id;
	private String resultado;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	
	
	
	
}
