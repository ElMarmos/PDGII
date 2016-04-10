package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the seguimiento database table.
 * 
 */
@Embeddable
public class SeguimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idPaciente;

	@Column(insertable=false, updatable=false)
	private int idEstado;

	public SeguimientoPK() {
	}
	public int getIdPaciente() {
		return this.idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getIdEstado() {
		return this.idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SeguimientoPK)) {
			return false;
		}
		SeguimientoPK castOther = (SeguimientoPK)other;
		return 
			(this.idPaciente == castOther.idPaciente)
			&& (this.idEstado == castOther.idEstado);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPaciente;
		hash = hash * prime + this.idEstado;
		
		return hash;
	}
}