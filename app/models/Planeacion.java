package models;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import play.db.jpa.GenericModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the programacion database table.
 * 
 */
@Entity
@NamedQuery(name="Planeacion.findAll", query="SELECT p FROM Planeacion p")
public class Planeacion extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose
	private int idPlaneacion;
	@Expose
	private Date fechaProgramacion;
	@Expose
	private Date fechaInicio;
	@Expose
	private Date fechaFin;
	@Expose
	private int numeroPacientes;
	@Expose
	private int tiempoProcesamiento;
	
	//bi-directional many-to-one association to CirugiaProfesionalsalud
	@OneToMany(mappedBy="planeacion")
	private List<Programacion> planeacionProgramaciones;

	//bi-directional many-to-one association to CamaPaciente
	@OneToMany(mappedBy="planeacion")
	private List<PlaneacionProfesionales> planeacionesProfesionales;
	
	//bi-directional many-to-one association to CamaPaciente
	@OneToMany(mappedBy="planeacion")
	private List<PlaneacionQuirofanos> planeacionesQuirofanos;
	
	public Planeacion() {
	}
	
	public int getIdPlaneacion() {
		return idPlaneacion;
	}
	public void setIdPlaneacion(int idPlaneacion) {
		this.idPlaneacion = idPlaneacion;
	}
	public Date getFechaProgramacion() {
		return fechaProgramacion;
	}
	public void setFechaProgramacion(Date fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getNumeroPacientes() {
		return numeroPacientes;
	}

	public void setNumeroPacientes(int numeroPacientes) {
		this.numeroPacientes = numeroPacientes;
	}

	public int getTiempoProcesamiento() {
		return tiempoProcesamiento;
	}

	public void setTiempoProcesamiento(int tiempoProcesamiento) {
		this.tiempoProcesamiento = tiempoProcesamiento;
	}

	public List<Programacion> getPlaneacionProgramaciones() {
		return planeacionProgramaciones;
	}

	public void setPlaneacionProgramaciones(
			List<Programacion> planeacionProgramaciones) {
		this.planeacionProgramaciones = planeacionProgramaciones;
	}

	public List<PlaneacionProfesionales> getPlaneacionesProfesionales() {
		return planeacionesProfesionales;
	}
	public void setPlaneacionesProfesionales(List<PlaneacionProfesionales> planeacionesProfesionales) {
		this.planeacionesProfesionales = planeacionesProfesionales;
	}
		
	public List<PlaneacionQuirofanos> getPlaneacionesQuirofanos() {
		return planeacionesQuirofanos;
	}

	public void setPlaneacionesQuirofanos(
			List<PlaneacionQuirofanos> planeacionesQuirofanos) {
		this.planeacionesQuirofanos = planeacionesQuirofanos;
	}

	public PlaneacionProfesionales addPlaneacionProfesionales(PlaneacionProfesionales planeacionProfesionales){
		getPlaneacionesProfesionales().add(planeacionProfesionales);
		planeacionProfesionales.setPlaneacion(this);
		return planeacionProfesionales;
	}
	
	public PlaneacionProfesionales removeCirugia(PlaneacionProfesionales planeacionProfesionales){
		getPlaneacionesProfesionales().remove(planeacionProfesionales);
		planeacionProfesionales.setPlaneacion(null);
		return planeacionProfesionales;
	}
	public Programacion addProgramacion(Programacion programacion){
		getPlaneacionProgramaciones().add(programacion);
		programacion.setPlaneacion(this);
		return programacion;
	}
	
	public Programacion removeCirugia(Programacion programacion){
		getPlaneacionProgramaciones().remove(programacion);
		programacion.setPlaneacion(null);
		return programacion;
	}
	
	public PlaneacionQuirofanos addPlaneacionQuirofano(PlaneacionQuirofanos planeacionQuirofanos){
		getPlaneacionesQuirofanos().add(planeacionQuirofanos);
		planeacionQuirofanos.setPlaneacion(this);
		return planeacionQuirofanos;
	}
	
	public PlaneacionQuirofanos removePlaneacionQuirofano(PlaneacionQuirofanos planeacionQuirofanos){
		getPlaneacionesQuirofanos().remove(planeacionQuirofanos);
		planeacionQuirofanos.setPlaneacion(null);
		return planeacionQuirofanos;
	}
	
}