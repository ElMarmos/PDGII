package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;


/**
 * The persistent class for the profesionalsaludespecialidad database table.
 * 
 */
@Entity
@NamedQuery(name="ProfesionalSaludEspecialidad.findAll", query="SELECT p FROM ProfesionalSaludEspecialidad p")
public class ProfesionalSaludEspecialidad extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

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