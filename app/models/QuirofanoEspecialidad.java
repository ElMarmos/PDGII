package models;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import java.sql.Time;


/**
 * The persistent class for the quirofanoespecialidad database table.
 * 
 */
@Entity
@NamedQuery(name="QuirofanoEspecialidad.findAll", query="SELECT q FROM QuirofanoEspecialidad q")
public class QuirofanoEspecialidad extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose
	public int idQuirofanoEspecialidad;
	
	//bi-directional many-to-one association to Quirofano
	@ManyToOne
	@JoinColumn(name="idQuirofano")
	private Quirofano quirofano;

	//bi-directional many-to-one association to Dotacion
	@ManyToOne
	@JoinColumn(name="idEspecialidad")
	@Expose
	private Especialidad especialidad;
	
	private double prioridad;

	public QuirofanoEspecialidad(Quirofano quirofano, Especialidad especialidad, double prioridad) {
		this.quirofano = quirofano;
		this.especialidad = especialidad;
		this.prioridad = prioridad;
	}

	public int getIdQuirofanoEspecialidad() {
		return idQuirofanoEspecialidad;
	}

	public void setIdQuirofanoEspecialidad(int idQuirofanoEspecialidad) {
		this.idQuirofanoEspecialidad = idQuirofanoEspecialidad;
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