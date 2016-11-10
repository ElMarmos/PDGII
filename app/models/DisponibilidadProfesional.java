package models;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import play.db.jpa.GenericModel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the disponibilidadprofesional database table.
 * 
 */
@Entity
@NamedQuery(name="DisponibilidadProfesional.findAll", query="SELECT d FROM DisponibilidadProfesional d")
public class DisponibilidadProfesional extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose
	private int idDisponibilidadProfesional;

	@Expose
	private byte habilitado;
	@Expose
	private Time horaFin;
	@Expose
	private Time horaInicio;

	@Lob
	@Expose
	private String periodoFin;

	@Lob
	@Expose
	private String periodoInicio;

	@Lob
	@Expose
	private String repeticion;

	//bi-directional many-to-one association to BloqueDisponibilidad
	@OneToMany(mappedBy="disponibilidadprofesional")
	private List<BloqueDisponibilidad> bloquedisponibilidads;

	//bi-directional many-to-one association to ProfesionalSalud
	@ManyToOne
	@JoinColumn(name="idProfesionalSalud")
	private ProfesionalSalud profesionalsalud;

	public DisponibilidadProfesional(ProfesionalSalud profesional, Time horaInicio, Time horaFin, String periodoInicio, String periodoFin, String repeticion) {
		this.profesionalsalud = profesional;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.periodoInicio = periodoInicio;
		this.periodoFin = periodoFin;
		this.repeticion = repeticion;
		
		this.bloquedisponibilidads = new ArrayList<BloqueDisponibilidad>();
	}

	public int getIdDisponibilidadProfesional() {
		return this.idDisponibilidadProfesional;
	}

	public void setIdDisponibilidadProfesional(int idDisponibilidadProfesional) {
		this.idDisponibilidadProfesional = idDisponibilidadProfesional;
	}

	public byte getHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(byte habilitado) {
		this.habilitado = habilitado;
	}

	public Time getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public Time getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getPeriodoFin() {
		return this.periodoFin;
	}

	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
	}

	public String getPeriodoInicio() {
		return this.periodoInicio;
	}

	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public String getRepeticion() {
		return this.repeticion;
	}

	public void setRepeticion(String repeticion) {
		this.repeticion = repeticion;
	}

	public List<BloqueDisponibilidad> getBloquedisponibilidads() {
		return this.bloquedisponibilidads;
	}

	public void setBloquedisponibilidads(List<BloqueDisponibilidad> bloquedisponibilidads) {
		this.bloquedisponibilidads = bloquedisponibilidads;
	}

	public BloqueDisponibilidad addBloquedisponibilidad(BloqueDisponibilidad bloquedisponibilidad) {
		getBloquedisponibilidads().add(bloquedisponibilidad);
		bloquedisponibilidad.setDisponibilidadprofesional(this);

		return bloquedisponibilidad;
	}

	public BloqueDisponibilidad removeBloquedisponibilidad(BloqueDisponibilidad bloquedisponibilidad) {
		getBloquedisponibilidads().remove(bloquedisponibilidad);
		bloquedisponibilidad.setDisponibilidadprofesional(null);

		return bloquedisponibilidad;
	}

	public ProfesionalSalud getProfesionalsalud() {
		return this.profesionalsalud;
	}

	public void setProfesionalsalud(ProfesionalSalud profesionalsalud) {
		this.profesionalsalud = profesionalsalud;
	}

}