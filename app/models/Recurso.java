package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.List;


/**
 * The persistent class for the recurso database table.
 * 
 */
@Entity
@NamedQuery(name="Recurso.findAll", query="SELECT r FROM Recurso r")
public class Recurso extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idRecurso;

	private String nombre;

	//bi-directional many-to-one association to RecursoCirugia
	@OneToMany(mappedBy="recurso")
	private List<RecursoCirugia> recursocirugias;

	public Recurso() {
	}

	public int getIdRecurso() {
		return this.idRecurso;
	}

	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<RecursoCirugia> getRecursocirugias() {
		return this.recursocirugias;
	}

	public void setRecursocirugias(List<RecursoCirugia> recursocirugias) {
		this.recursocirugias = recursocirugias;
	}

	public RecursoCirugia addRecursocirugia(RecursoCirugia recursocirugia) {
		getRecursocirugias().add(recursocirugia);
		recursocirugia.setRecurso(this);

		return recursocirugia;
	}

	public RecursoCirugia removeRecursocirugia(RecursoCirugia recursocirugia) {
		getRecursocirugias().remove(recursocirugia);
		recursocirugia.setRecurso(null);

		return recursocirugia;
	}

}