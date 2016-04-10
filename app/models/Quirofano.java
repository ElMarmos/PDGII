package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.List;


/**
 * The persistent class for the quirofano database table.
 * 
 */
@Entity
@NamedQuery(name="Quirofano.findAll", query="SELECT q FROM Quirofano q")
public class Quirofano extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idQuirofano;

	private String nombreQuirofano;

	//bi-directional many-to-one association to Cirugia
	@OneToMany(mappedBy="quirofano")
	private List<Cirugia> cirugias;

	//bi-directional many-to-one association to Pabellon
	@ManyToOne
	@JoinColumn(name="idPabellon")
	private Pabellon pabellon;

	//bi-directional many-to-many association to Dotacion
	@ManyToMany
	@JoinTable(
		name="quirofanodotacion"
		, joinColumns={
			@JoinColumn(name="idQuirofano")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idDotacion")
			}
		)
	private List<Dotacion> dotacions;

	public Quirofano() {
	}

	public int getIdQuirofano() {
		return this.idQuirofano;
	}

	public void setIdQuirofano(int idQuirofano) {
		this.idQuirofano = idQuirofano;
	}

	public String getNombreQuirofano() {
		return this.nombreQuirofano;
	}

	public void setNombreQuirofano(String nombreQuirofano) {
		this.nombreQuirofano = nombreQuirofano;
	}

	public List<Cirugia> getCirugias() {
		return this.cirugias;
	}

	public void setCirugias(List<Cirugia> cirugias) {
		this.cirugias = cirugias;
	}

	public Cirugia addCirugia(Cirugia cirugia) {
		getCirugias().add(cirugia);
		cirugia.setQuirofano(this);

		return cirugia;
	}

	public Cirugia removeCirugia(Cirugia cirugia) {
		getCirugias().remove(cirugia);
		cirugia.setQuirofano(null);

		return cirugia;
	}

	public Pabellon getPabellon() {
		return this.pabellon;
	}

	public void setPabellon(Pabellon pabellon) {
		this.pabellon = pabellon;
	}

	public List<Dotacion> getDotacions() {
		return this.dotacions;
	}

	public void setDotacions(List<Dotacion> dotacions) {
		this.dotacions = dotacions;
	}

}