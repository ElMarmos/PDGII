package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;


/**
 * The persistent class for the cirugiaprofesionalsalud database table.
 * 
 */
@Entity
@NamedQuery(name="CirugiaProfesionalSalud.findAll", query="SELECT c FROM CirugiaProfesionalSalud c")
public class CirugiaProfesionalSalud extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCirugiaProfesionalSalud;
	
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

	
	public int getIdCirugiaProfesionalSalud() {
		return idCirugiaProfesionalSalud;
	}


	public void setIdCirugiaProfesionalSalud(int idCirugiaProfesionalSalud) {
		this.idCirugiaProfesionalSalud = idCirugiaProfesionalSalud;
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