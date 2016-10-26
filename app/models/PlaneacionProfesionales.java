package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import java.sql.Timestamp;


/**
 * The persistent class for the camapaciente database table.
 * 
 */
@Entity
@NamedQuery(name="PlaneacionProfesionales.findAll", query="SELECT c FROM PlaneacionProfesionales c")
public class PlaneacionProfesionales extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPlaneacionProfesional;
	
	private String rol;

	//bi-directional many-to-one association to Cama
	@ManyToOne
	@JoinColumn(name="idProfesionalSalud")
	private ProfesionalSalud profesionalSalud;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="idPlaneacion")
	private Planeacion planeacion;

	public PlaneacionProfesionales() {
	}

	public int getIdPlaneacionProfesional() {
		return idPlaneacionProfesional;
	}



	public void setIdPlaneacionProfesional(int idPlaneacionProfesional) {
		this.idPlaneacionProfesional = idPlaneacionProfesional;
	}



	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public ProfesionalSalud getProfesionalSalud() {
		return profesionalSalud;
	}

	public void setProfesionalSalud(ProfesionalSalud profesionalSalud) {
		this.profesionalSalud = profesionalSalud;
	}

	public Planeacion getPlaneacion() {
		return planeacion;
	}

	public void setPlaneacion(Planeacion planeacion) {
		this.planeacion = planeacion;
	}

	

}