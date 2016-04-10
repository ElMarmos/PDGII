package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;


/**
 * The persistent class for the cirujano database table.
 * 
 */
@Entity
@NamedQuery(name="Cirujano.findAll", query="SELECT c FROM Cirujano c")
public class Cirujano extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCirujano;

	//bi-directional many-to-one association to ProfesionalSalud
	@ManyToOne
	@JoinColumn(name="idProfesionalSalud")
	private ProfesionalSalud profesionalsalud;

	public Cirujano() {
	}

	public int getIdCirujano() {
		return this.idCirujano;
	}

	public void setIdCirujano(int idCirujano) {
		this.idCirujano = idCirujano;
	}

	public ProfesionalSalud getProfesionalsalud() {
		return this.profesionalsalud;
	}

	public void setProfesionalsalud(ProfesionalSalud profesionalsalud) {
		this.profesionalsalud = profesionalsalud;
	}

}