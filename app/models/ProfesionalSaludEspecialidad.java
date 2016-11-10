package models;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;


/**
 * The persistent class for the profesionalsaludespecialidad database table.
 * 
 */
@Entity
@NamedQuery(name="ProfesionalSaludEspecialidad.findAll", query="SELECT p FROM ProfesionalSaludEspecialidad p")
public class ProfesionalSaludEspecialidad extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose
	private int IdProfesionalSaludEspecialista;
	@Expose
	private int prioridad;

	//bi-directional many-to-one association to Especialidad
	@ManyToOne
	@JoinColumn(name="idEspecialidad")
	@Expose
	private Especialidad especialidad;

	//bi-directional many-to-one association to ProfesionalSalud
	@ManyToOne
	@JoinColumn(name="idProfesionalSalud")
	private ProfesionalSalud profesionalsalud;

	public ProfesionalSaludEspecialidad(ProfesionalSalud profesional, Especialidad especialidad, int prioridad) {
		this.profesionalsalud = profesional;
		this.especialidad = especialidad;
		this.prioridad = prioridad;
	}

	public int getIdProfesionalSaludEspecialista() {
		return IdProfesionalSaludEspecialista;
	}

	public void setIdProfesionalSaludEspecialista(int idProfesionalSaludEspecialista) {
		IdProfesionalSaludEspecialista = idProfesionalSaludEspecialista;
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