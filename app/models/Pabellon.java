package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.ArrayList;
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPabellon;

	private String nombre;

	//bi-directional many-to-one association to Quirofano
	@OneToMany(mappedBy="pabellon")
	private List<Quirofano> quirofanos;

	public Pabellon(String nombre) {
		this.nombre = nombre;
		
		this.quirofanos = new ArrayList<Quirofano>();
	}

	public int getIdPabellon() {
		return this.idPabellon;
	}

	public void setIdPabellon(int idPabellon) {
		this.idPabellon = idPabellon;
	}

	public String get_nombre() {
		return this.nombre;
	}

	public void set_nombre(String _nombre) {
		this.nombre = _nombre;
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