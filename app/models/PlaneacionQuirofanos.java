package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import java.sql.Timestamp;


/**
 * The persistent class for the camapaciente database table.
 * 
 */
@Entity
@NamedQuery(name="PlaneacionQuirofanos.findAll", query="SELECT c FROM PlaneacionQuirofanos c")
public class PlaneacionQuirofanos extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPlaneacionQuirofano;
	
	//bi-directional many-to-one association to Cama
	@ManyToOne
	@JoinColumn(name="idQuirofano", insertable=true, updatable=true)
	private Quirofano quirofano;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="idPlaneacion")
	private Planeacion planeacion;

	public PlaneacionQuirofanos() {
	}

	public int getIdPlaneacionQuirofano() {
		return idPlaneacionQuirofano;
	}

	public void setIdPlaneacionQuirofano(int idPlaneacionQuirofano) {
		this.idPlaneacionQuirofano = idPlaneacionQuirofano;
	}

	public Quirofano getQuirofano() {
		return quirofano;
	}

	public void setQuirofano(Quirofano quirofano) {
		this.quirofano = quirofano;
	}

	public Planeacion getPlaneacion() {
		return planeacion;
	}

	public void setPlaneacion(Planeacion planeacion) {
		this.planeacion = planeacion;
	}

	

}