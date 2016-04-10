package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cirugiaprocedimiento database table.
 * 
 */
@Embeddable
public class CirugiaProcedimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idCirugia;

	@Column(insertable=false, updatable=false)
	private int codigoProcedimiento;

	public CirugiaProcedimientoPK() {
	}
	public int getIdCirugia() {
		return this.idCirugia;
	}
	public void setIdCirugia(int idCirugia) {
		this.idCirugia = idCirugia;
	}
	public int getCodigoProcedimiento() {
		return this.codigoProcedimiento;
	}
	public void setCodigoProcedimiento(int codigoProcedimiento) {
		this.codigoProcedimiento = codigoProcedimiento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CirugiaProcedimientoPK)) {
			return false;
		}
		CirugiaProcedimientoPK castOther = (CirugiaProcedimientoPK)other;
		return 
			(this.idCirugia == castOther.idCirugia)
			&& (this.codigoProcedimiento == castOther.codigoProcedimiento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCirugia;
		hash = hash * prime + this.codigoProcedimiento;
		
		return hash;
	}
}