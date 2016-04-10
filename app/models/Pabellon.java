package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.List;


/**
 * The persistent class for the pabellon database table.
 * 
 */
@Entity
@NamedQuery(name="Pabellon.findAll", query="SELECT p FROM Pabellon p")
public class Pabellon extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPabellon;

	@Column(name="` nombre`")
	private String _nombre;

	//bi-directional many-to-one association to Quirofano
	@OneToMany(mappedBy="pabellon")
	private List<Quirofano> quirofanos;

	public Pabellon() {
	}

	public int getIdPabellon() {
		return this.idPabellon;
	}

	public void setIdPabellon(int idPabellon) {
		this.idPabellon = idPabellon;
	}

	public String get_nombre() {
		return this._nombre;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}

	public List<Quirofano> getQuirofanos() {
		return this.quirofanos;
	}

	public void setQuirofanos(List<Quirofano> quirofanos) {
		this.quirofanos = quirofanos;
	}

	public Quirofano addQuirofano(Quirofano quirofano) {
		getQuirofanos().add(quirofano);
		quirofano.setPabellon(this);

		return quirofano;
	}

	public Quirofano removeQuirofano(Quirofano quirofano) {
		getQuirofanos().remove(quirofano);
		quirofano.setPabellon(null);

		return quirofano;
	}

}