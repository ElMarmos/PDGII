package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;


/**
 * The persistent class for the patientologia database table.
 * 
 */
@Entity
@NamedQuery(name="Patientologia.findAll", query="SELECT p FROM Patientologia p")
public class Patientologia extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPatientologia;
	
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

	public int getIdPatientologia() {
		return idPatientologia;
	}

	public void setIdPatientologia(int idPatientologia) {
		this.idPatientologia = idPatientologia;
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