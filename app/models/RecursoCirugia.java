package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;


/**
 * The persistent class for the recursocirugia database table.
 * 
 */
@Entity
@NamedQuery(name="RecursoCirugia.findAll", query="SELECT r FROM RecursoCirugia r")
public class RecursoCirugia extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	private int cantidad;

	//bi-directional many-to-one association to Cirugia
	@ManyToOne
	@JoinColumn(name="idCirugia")
	private Cirugia cirugia;

	//bi-directional many-to-one association to Recurso
	@ManyToOne
	@JoinColumn(name="idRecurso")
	private Recurso recurso;

	public RecursoCirugia() {
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Cirugia getCirugia() {
		return this.cirugia;
	}

	public void setCirugia(Cirugia cirugia) {
		this.cirugia = cirugia;
	}

	public Recurso getRecurso() {
		return this.recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

}