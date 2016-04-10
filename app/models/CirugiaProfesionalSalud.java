package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;


/**
 * The persistent class for the cirugiaprofesionalsalud database table.
 * 
 */
@Entity
@NamedQuery(name="CirugiaProfesionalSalud.findAll", query="SELECT c FROM CirugiaProfesionalSalud c")
public class CirugiaProfesionalSalud extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CirugiaProfesionalSaludPK id;

	private String rol;

	//bi-directional many-to-one association to Cirugia
	@ManyToOne
	@JoinColumn(name="idCirugia")
	private Cirugia cirugia;

	//bi-directional many-to-one association to ProfesionalSalud
	@ManyToOne
	@JoinColumn(name="idProfesionalSalud")
	private ProfesionalSalud profesionalsalud;

	public CirugiaProfesionalSalud() {
	}

	public CirugiaProfesionalSaludPK getId() {
		return this.id;
	}

	public void setId(CirugiaProfesionalSaludPK id) {
		this.id = id;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Cirugia getCirugia() {
		return this.cirugia;
	}

	public void setCirugia(Cirugia cirugia) {
		this.cirugia = cirugia;
	}

	public ProfesionalSalud getProfesionalsalud() {
		return this.profesionalsalud;
	}

	public void setProfesionalsalud(ProfesionalSalud profesionalsalud) {
		this.profesionalsalud = profesionalsalud;
	}

}