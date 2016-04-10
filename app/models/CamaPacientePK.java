package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the camapaciente database table.
 * 
 */
@Embeddable
public class CamaPacientePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idCama;

	@Column(insertable=false, updatable=false)
	private int idPaciente;

	public CamaPacientePK() {
	}
	public int getIdCama() {
		return this.idCama;
	}
	public void setIdCama(int idCama) {
		this.idCama = idCama;
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
		if (!(other instanceof CamaPacientePK)) {
			return false;
		}
		CamaPacientePK castOther = (CamaPacientePK)other;
		return 
			(this.idCama == castOther.idCama)
			&& (this.idPaciente == castOther.idPaciente);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCama;
		hash = hash * prime + this.idPaciente;
		
		return hash;
	}
}