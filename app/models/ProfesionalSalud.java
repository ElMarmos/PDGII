package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.ArrayList;
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idProfesionalSalud;

	private String apellidos;

	@Lob
	private String contrato;

	private String nombres;

	@Lob
	private String profesion;

	// bi-directional many-to-one association to CirugiaProfesionalsalud
	@OneToMany(mappedBy = "profesionalsalud")
	private List<CirugiaProfesionalSalud> cirugiaprofesionalsaluds;

	// bi-directional many-to-one association to DisponibilidadProfesional
	@OneToMany(mappedBy = "profesionalsalud")
	private List<DisponibilidadProfesional> disponibilidadprofesionals;

	// bi-directional many-to-one association to ProfesionalSaludEspecialidad
	@OneToMany(mappedBy = "profesionalsalud")
	private List<ProfesionalSaludEspecialidad> profesionalsaludespecialidads;
	
	//bi-directional many-to-one association to CamaPaciente
	@OneToMany(mappedBy="profesionalSalud")
	private List<PlaneacionProfesionales> planeacionesProfesionales;

	public ProfesionalSalud(String nombres, String apellidos, String profesion, String contrato) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.profesion = profesion;
		this.contrato = contrato;
		this.cirugiaprofesionalsaluds = new ArrayList<>();
		this.disponibilidadprofesionals = new ArrayList<>();
		this.profesionalsaludespecialidads = new ArrayList<>();
		this.planeacionesProfesionales = new ArrayList<>();
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
	
	public List<PlaneacionProfesionales> getPlaneacionesProfesionales() {
		return planeacionesProfesionales;
	}
	public void setPlaneacionesProfesionales(List<PlaneacionProfesionales> planeacionesProfesionales) {
		this.planeacionesProfesionales = planeacionesProfesionales;
	}
	public PlaneacionProfesionales addPlaneacionProfesionales(PlaneacionProfesionales planeacionProfesionales){
		getPlaneacionesProfesionales().add(planeacionProfesionales);
		planeacionProfesionales.setProfesionalSalud(this);
		return planeacionProfesionales;
	}
	
	public PlaneacionProfesionales removeCirugia(PlaneacionProfesionales planeacionProfesionales){
		getPlaneacionesProfesionales().remove(planeacionProfesionales);
		planeacionProfesionales.setProfesionalSalud(null);
		return planeacionProfesionales;
	}

}