package mx.ipn.escom.mps.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import mx.ipn.escom.mau.entities.Persona;

@Entity
@Table(name="tps04_dueno")
@PrimaryKeyJoinColumn(name="id_dueno", referencedColumnName="id_persona")
public class Dueno extends Persona implements Serializable{
	
	@OneToMany
	@JoinColumn(name="id_dueno", referencedColumnName="id_dueno", updatable=false, insertable=false)
	private List<Cafeteria> cafeterias;

	public List<Cafeteria> getCafeterias() {
		return cafeterias;
	}

	public void setCafeterias(List<Cafeteria> cafeterias) {
		this.cafeterias = cafeterias;
	}
	
	
}
