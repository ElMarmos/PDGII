package models;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import play.db.jpa.GenericModel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the disponibilidadquirofano database table.
 * 
 */
@Entity
@NamedQuery(name="DisponibilidadQuirofano.findAll", query="SELECT d FROM DisponibilidadQuirofano d")
public class DisponibilidadQuirofano extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose
	private int idDisponibilidadQuirofano;

	@Expose
	private boolean habilitado;

	@Expose
	private Time horaFin;
	@Expose
	private Time horaInicio;

	@Lob
	@Expose
	private String repeticion;

	//bi-directional many-to-one association to ProfesionalSalud
	@ManyToOne
	@JoinColumn(name="idQuirofano")
	private Quirofano quirofano;

	public DisponibilidadQuirofano(Quirofano quirofano, Time horaInicio, Time horaFin, String repeticion) {
		this.quirofano = quirofano;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.repeticion = repeticion;
		
	}

	public boolean getHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Time getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public Time getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getRepeticion() {
		return this.repeticion;
	}

	public void setRepeticion(String repeticion) {
		this.repeticion = repeticion;
	}

	public int getIdDisponibilidadQuirofano() {
		return idDisponibilidadQuirofano;
	}

	public void setIdDisponibilidadQuirofano(int idDisponibilidadQuirofano) {
		this.idDisponibilidadQuirofano = idDisponibilidadQuirofano;
	}

	public Quirofano getQuirofano() {
		return quirofano;
	}

	public void setQuirofano(Quirofano quirofano) {
		this.quirofano = quirofano;
	}

	

}