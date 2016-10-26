package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the dotacion database table.
 * 
 */
@Entity
@NamedQuery(name="Dotacion.findAll", query="SELECT d FROM Dotacion d")
public class Dotacion extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idDotacion;

	private String nombre;

	//bi-directional many-to-many association to Quirofano
	@OneToMany(mappedBy="dotacion")
	private List<QuirofanoDotacion> quirofanoDotaciones;

	public Dotacion(String nombre) {
		this.nombre = nombre;
		quirofanoDotaciones = new ArrayList<>();
	}

	public int getIdDotacion() {
		return this.idDotacion;
	}

	public void setIdDotacion(int idDotacion) {
		this.idDotacion = idDotacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<QuirofanoDotacion> getQuirofanoDotaciones() {
		return quirofanoDotaciones;
	}

	public void setQuirofanoDotaciones(List<QuirofanoDotacion> quirofanoDotaciones) {
		this.quirofanoDotaciones = quirofanoDotaciones;
	}
	public QuirofanoDotacion addQuirofanoDotaciones(QuirofanoDotacion quirofanoDotacion){
		getQuirofanoDotaciones().add(quirofanoDotacion);
		quirofanoDotacion.setDotacion(this);
		return quirofanoDotacion;
	}
	public QuirofanoDotacion removeQuirofanoDotaciones(QuirofanoDotacion quirofanoDotacion){
		getQuirofanoDotaciones().add(quirofanoDotacion);
		quirofanoDotacion.setDotacion(null);
		return quirofanoDotacion;
	}
	
}