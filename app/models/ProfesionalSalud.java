package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.List;

/**
 * The persistent class for the profesionalsalud database table.
 * 
 */
@Entity
@NamedQuery(name = "ProfesionalSalud.findAll", query = "SELECT p FROM ProfesionalSalud p")
public class ProfesionalSalud extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idProfesionalSalud;

	private String apellidos;

	@Lob
	private String contrato;

	private String nombres;

	@Lob
	private String profesion;

	// bi-directional many-to-one association to Anestesiologo
	@OneToMany(mappedBy = "profesionalsalud")
	private List<Anestesiologo> anestesiologos;

	// bi-directional many-to-one association to CirugiaProfesionalsalud
	@OneToMany(mappedBy = "profesionalsalud")
	private List<CirugiaProfesionalSalud> cirugiaprofesionalsaluds;

	// bi-directional many-to-one association to Cirujano
	@OneToMany(mappedBy = "profesionalsalud")
	private List<Cirujano> cirujanos;

	// bi-directional many-to-one association to DisponibilidadProfesional
	@OneToMany(mappedBy = "profesionalsalud")
	private List<DisponibilidadProfesional> disponibilidadprofesionals;

	// bi-directional many-to-one association to ProfesionalSaludEspecialidad
	@OneToMany(mappedBy = "profesionalsalud")
	private List<ProfesionalSaludEspecialidad> profesionalsaludespecialidads;

	public ProfesionalSalud() {
	}

	public int getIdProfesionalSalud() {
		return this.idProfesionalSalud;
	}

	public void setIdProfesionalSalud(int idProfesionalSalud) {
		this.idProfesionalSalud = idProfesionalSalud;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContrato() {
		return this.contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getProfesion() {
		return this.profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public List<Anestesiologo> getAnestesiologos() {
		return this.anestesiologos;
	}

	public void setAnestesiologos(List<Anestesiologo> anestesiologos) {
		this.anestesiologos = anestesiologos;
	}

	public Anestesiologo addAnestesiologo(Anestesiologo anestesiologo) {
		getAnestesiologos().add(anestesiologo);
		anestesiologo.setProfesionalsalud(this);

		return anestesiologo;
	}

	public Anestesiologo removeAnestesiologo(Anestesiologo anestesiologo) {
		getAnestesiologos().remove(anestesiologo);
		anestesiologo.setProfesionalsalud(null);

		return anestesiologo;
	}

	public List<CirugiaProfesionalSalud> getCirugiaprofesionalsaluds() {
		return this.cirugiaprofesionalsaluds;
	}

	public void setCirugiaprofesionalsaluds(
			List<CirugiaProfesionalSalud> cirugiaprofesionalsaluds) {
		this.cirugiaprofesionalsaluds = cirugiaprofesionalsaluds;
	}

	public CirugiaProfesionalSalud addCirugiaprofesionalsalud(
			CirugiaProfesionalSalud cirugiaprofesionalsalud) {
		getCirugiaprofesionalsaluds().add(cirugiaprofesionalsalud);
		cirugiaprofesionalsalud.setProfesionalsalud(this);

		return cirugiaprofesionalsalud;
	}

	public CirugiaProfesionalSalud removeCirugiaprofesionalsalud(
			CirugiaProfesionalSalud cirugiaprofesionalsalud) {
		getCirugiaprofesionalsaluds().remove(cirugiaprofesionalsalud);
		cirugiaprofesionalsalud.setProfesionalsalud(null);

		return cirugiaprofesionalsalud;
	}

	public List<Cirujano> getCirujanos() {
		return this.cirujanos;
	}

	public void setCirujanos(List<Cirujano> cirujanos) {
		this.cirujanos = cirujanos;
	}

	public Cirujano addCirujano(Cirujano cirujano) {
		getCirujanos().add(cirujano);
		cirujano.setProfesionalsalud(this);

		return cirujano;
	}

	public Cirujano removeCirujano(Cirujano cirujano) {
		getCirujanos().remove(cirujano);
		cirujano.setProfesionalsalud(null);

		return cirujano;
	}

	public List<DisponibilidadProfesional> getDisponibilidadprofesionals() {
		return this.disponibilidadprofesionals;
	}

	public void setDisponibilidadprofesionals(
			List<DisponibilidadProfesional> disponibilidadprofesionals) {
		this.disponibilidadprofesionals = disponibilidadprofesionals;
	}

	public DisponibilidadProfesional addDisponibilidadprofesional(
			DisponibilidadProfesional disponibilidadprofesional) {
		getDisponibilidadprofesionals().add(disponibilidadprofesional);
		disponibilidadprofesional.setProfesionalsalud(this);

		return disponibilidadprofesional;
	}

	public DisponibilidadProfesional removeDisponibilidadprofesional(
			DisponibilidadProfesional disponibilidadprofesional) {
		getDisponibilidadprofesionals().remove(disponibilidadprofesional);
		disponibilidadprofesional.setProfesionalsalud(null);

		return disponibilidadprofesional;
	}

	public List<ProfesionalSaludEspecialidad> getProfesionalsaludespecialidads() {
		return this.profesionalsaludespecialidads;
	}

	public void setProfesionalsaludespecialidads(
			List<ProfesionalSaludEspecialidad> profesionalsaludespecialidads) {
		this.profesionalsaludespecialidads = profesionalsaludespecialidads;
	}

	public ProfesionalSaludEspecialidad addProfesionalsaludespecialidad(
			ProfesionalSaludEspecialidad profesionalsaludespecialidad) {
		getProfesionalsaludespecialidads().add(profesionalsaludespecialidad);
		profesionalsaludespecialidad.setProfesionalsalud(this);

		return profesionalsaludespecialidad;
	}

	public ProfesionalSaludEspecialidad removeProfesionalsaludespecialidad(
			ProfesionalSaludEspecialidad profesionalsaludespecialidad) {
		getProfesionalsaludespecialidads().remove(profesionalsaludespecialidad);
		profesionalsaludespecialidad.setProfesionalsalud(null);

		return profesionalsaludespecialidad;
	}

}