package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cirugiaprofesionalsalud database table.
 * 
 */
@Embeddable
public class CirugiaProfesionalSaludPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idCirugia;

	@Column(insertable=false, updatable=false)
	private int idProfesionalSalud;

	public CirugiaProfesionalSaludPK() {
	}
	public int getIdCirugia() {
		return this.idCirugia;
	}
	public void setIdCirugia(int idCirugia) {
		this.idCirugia = idCirugia;
	}
	public int getIdProfesionalSalud() {
		return this.idProfesionalSalud;
	}
	public void setIdProfesionalSalud(int idProfesionalSalud) {
		this.idProfesionalSalud = idProfesionalSalud;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CirugiaProfesionalSaludPK)) {
			return false;
		}
		CirugiaProfesionalSaludPK castOther = (CirugiaProfesionalSaludPK)other;
		return 
			(this.idCirugia == castOther.idCirugia)
			&& (this.idProfesionalSalud == castOther.idProfesionalSalud);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCirugia;
		hash = hash * prime + this.idProfesionalSalud;
		
		return hash;
	}
}