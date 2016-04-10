package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;


/**
 * The persistent class for the profesionalsaludespecialidad database table.
 * 
 */
@Entity
@NamedQuery(name="ProfesionalSaludEspecialidad.findAll", query="SELECT p FROM ProfesionalSaludEspecialidad p")
public class ProfesionalSaludEspecialidad extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProfesionalSaludEspecialidadPK id;

	private int prioridad;

	//bi-directional many-to-one association to Especialidad
	@ManyToOne
	@JoinColumn(name="idEspecialidad")
	private Especialidad especialidad;

	//bi-directional many-to-one association to ProfesionalSalud
	@ManyToOne
	@JoinColumn(name="idProfesionalSalud")
	private ProfesionalSalud profesionalsalud;

	public ProfesionalSaludEspecialidad() {
	}

	public ProfesionalSaludEspecialidadPK getId() {
		return this.id;
	}

	public void setId(ProfesionalSaludEspecialidadPK id) {
		this.id = id;
	}

	public int getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public ProfesionalSalud getProfesionalsalud() {
		return this.profesionalsalud;
	}

	public void setProfesionalsalud(ProfesionalSalud profesionalsalud) {
		this.profesionalsalud = profesionalsalud;
	}

}