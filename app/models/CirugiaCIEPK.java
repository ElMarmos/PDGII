package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cirugiacie database table.
 * 
 */
@Embeddable
public class CirugiaCIEPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idCirugia;

	@Column(insertable=false, updatable=false)
	private int idCIE;

	public CirugiaCIEPK() {
	}
	public int getIdCirugia() {
		return this.idCirugia;
	}
	public void setIdCirugia(int idCirugia) {
		this.idCirugia = idCirugia;
	}
	public int getIdCIE() {
		return this.idCIE;
	}
	public void setIdCIE(int idCIE) {
		this.idCIE = idCIE;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CirugiaCIEPK)) {
			return false;
		}
		CirugiaCIEPK castOther = (CirugiaCIEPK)other;
		return 
			(this.idCirugia == castOther.idCirugia)
			&& (this.idCIE == castOther.idCIE);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCirugia;
		hash = hash * prime + this.idCIE;
		
		return hash;
	}
}