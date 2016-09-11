package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.List;


/**
 * The persistent class for the patologia database table.
 * 
 */
@Entity
@NamedQuery(name="Patologia.findAll", query="SELECT p FROM Patologia p")
public class Patologia extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPatologia;

	private String nombre;

	//bi-directional many-to-one association to Patientologia
	@OneToMany(mappedBy="patologia")
	private List<Patientologia> patientologias;

	public Patologia() {
	}

	public int getIdPatologia() {
		return this.idPatologia;
	}

	public void setIdPatologia(int idPatologia) {
		this.idPatologia = idPatologia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Patientologia> getPatientologias() {
		return this.patientologias;
	}

	public void setPatientologias(List<Patientologia> patientologias) {
		this.patientologias = patientologias;
	}

	public Patientologia addPatientologia(Patientologia patientologia) {
		getPatientologias().add(patientologia);
		patientologia.setPatologia(this);

		return patientologia;
	}

	public Patientologia removePatientologia(Patientologia patientologia) {
		getPatientologias().remove(patientologia);
		patientologia.setPatologia(null);

		return patientologia;
	}

}