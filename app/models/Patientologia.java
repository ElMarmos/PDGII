package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;


/**
 * The persistent class for the patientologia database table.
 * 
 */
@Entity
@NamedQuery(name="Patientologia.findAll", query="SELECT p FROM Patientologia p")
public class Patientologia extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PatientologiaPK id;

	private String nombre;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="idPaciente")
	private Paciente paciente;

	//bi-directional many-to-one association to Patologia
	@ManyToOne
	@JoinColumn(name="idPatologia")
	private Patologia patologia;

	public Patientologia() {
	}

	public PatientologiaPK getId() {
		return this.id;
	}

	public void setId(PatientologiaPK id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Patologia getPatologia() {
		return this.patologia;
	}

	public void setPatologia(Patologia patologia) {
		this.patologia = patologia;
	}

}