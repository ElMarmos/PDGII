package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the profesionalsaludespecialidad database table.
 * 
 */
@Embeddable
public class ProfesionalSaludEspecialidadPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idProfesionalSalud;

	@Column(insertable=false, updatable=false)
	private int idEspecialidad;

	public ProfesionalSaludEspecialidadPK() {
	}
	public int getIdProfesionalSalud() {
		return this.idProfesionalSalud;
	}
	public void setIdProfesionalSalud(int idProfesionalSalud) {
		this.idProfesionalSalud = idProfesionalSalud;
	}
	public int getIdEspecialidad() {
		return this.idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProfesionalSaludEspecialidadPK)) {
			return false;
		}
		ProfesionalSaludEspecialidadPK castOther = (ProfesionalSaludEspecialidadPK)other;
		return 
			(this.idProfesionalSalud == castOther.idProfesionalSalud)
			&& (this.idEspecialidad == castOther.idEspecialidad);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idProfesionalSalud;
		hash = hash * prime + this.idEspecialidad;
		
		return hash;
	}
}