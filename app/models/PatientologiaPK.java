package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the patientologia database table.
 * 
 */
@Embeddable
public class PatientologiaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idPatologia;

	@Column(insertable=false, updatable=false)
	private int idPaciente;

	public PatientologiaPK() {
	}
	public int getIdPatologia() {
		return this.idPatologia;
	}
	public void setIdPatologia(int idPatologia) {
		this.idPatologia = idPatologia;
	}
	public int getIdPaciente() {
		return this.idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PatientologiaPK)) {
			return false;
		}
		PatientologiaPK castOther = (PatientologiaPK)other;
		return 
			(this.idPatologia == castOther.idPatologia)
			&& (this.idPaciente == castOther.idPaciente);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPatologia;
		hash = hash * prime + this.idPaciente;
		
		return hash;
	}
}