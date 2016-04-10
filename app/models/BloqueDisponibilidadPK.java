package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the bloquedisponibilidad database table.
 * 
 */
@Embeddable
public class BloqueDisponibilidadPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idDisponibilidadProfesional;

	@Column(insertable=false, updatable=false)
	private int idBloqueQuirurgico;

	public BloqueDisponibilidadPK() {
	}
	public int getIdDisponibilidadProfesional() {
		return this.idDisponibilidadProfesional;
	}
	public void setIdDisponibilidadProfesional(int idDisponibilidadProfesional) {
		this.idDisponibilidadProfesional = idDisponibilidadProfesional;
	}
	public int getIdBloqueQuirurgico() {
		return this.idBloqueQuirurgico;
	}
	public void setIdBloqueQuirurgico(int idBloqueQuirurgico) {
		this.idBloqueQuirurgico = idBloqueQuirurgico;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BloqueDisponibilidadPK)) {
			return false;
		}
		BloqueDisponibilidadPK castOther = (BloqueDisponibilidadPK)other;
		return 
			(this.idDisponibilidadProfesional == castOther.idDisponibilidadProfesional)
			&& (this.idBloqueQuirurgico == castOther.idBloqueQuirurgico);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idDisponibilidadProfesional;
		hash = hash * prime + this.idBloqueQuirurgico;
		
		return hash;
	}
}