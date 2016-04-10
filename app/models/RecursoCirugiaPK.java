package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the recursocirugia database table.
 * 
 */
@Embeddable
public class RecursoCirugiaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idRecurso;

	@Column(insertable=false, updatable=false)
	private int idCirugia;

	public RecursoCirugiaPK() {
	}
	public int getIdRecurso() {
		return this.idRecurso;
	}
	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}
	public int getIdCirugia() {
		return this.idCirugia;
	}
	public void setIdCirugia(int idCirugia) {
		this.idCirugia = idCirugia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RecursoCirugiaPK)) {
			return false;
		}
		RecursoCirugiaPK castOther = (RecursoCirugiaPK)other;
		return 
			(this.idRecurso == castOther.idRecurso)
			&& (this.idCirugia == castOther.idCirugia);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idRecurso;
		hash = hash * prime + this.idCirugia;
		
		return hash;
	}
}