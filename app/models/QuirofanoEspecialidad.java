package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import java.sql.Time;


/**
 * The persistent class for the quirofanoespecialidad database table.
 * 
 */
@Entity
@NamedQuery(name="QuirofanoEspecialidad.findAll", query="SELECT q FROM QuirofanoEspecialidad q")
public class QuirofanoEspecialidad extends Model implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	//bi-directional many-to-one association to Quirofano
	@ManyToOne
	@JoinColumn(name="idQuirofano")
	private Quirofano quirofano;

	//bi-directional many-to-one association to Dotacion
	@ManyToOne
	@JoinColumn(name="idEspecialidad")
	private Especialidad especialidad;
	
	private double prioridad;

	public QuirofanoEspecialidad() {
	}

	public Quirofano getQuirofano() {
		return quirofano;
	}

	public void setQuirofano(Quirofano quirofano) {
		this.quirofano = quirofano;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad dotacion) {
		this.especialidad = dotacion;
	}

	public double getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(double prioridad) {
		this.prioridad = prioridad;
	}
	
	
	

}