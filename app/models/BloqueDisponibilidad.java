package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the bloquedisponibilidad database table.
 * 
 */
@Entity
@NamedQuery(name="BloqueDisponibilidad.findAll", query="SELECT b FROM BloqueDisponibilidad b")
public class BloqueDisponibilidad extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BloqueDisponibilidadPK id;

	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	private Time horaFin;

	private Time horaInicio;

	//bi-directional many-to-one association to BloqueQuirurgico
	@ManyToOne
	@JoinColumn(name="idBloqueQuirurgico")
	private BloqueQuirurgico bloquequirurgico;

	//bi-directional many-to-one association to DisponibilidadProfesional
	@ManyToOne
	@JoinColumn(name="idDisponibilidadProfesional")
	private DisponibilidadProfesional disponibilidadprofesional;

	public BloqueDisponibilidad() {
	}

	public BloqueDisponibilidadPK getId() {
		return this.id;
	}

	public void setId(BloqueDisponibilidadPK id) {
		this.id = id;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
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

	public BloqueQuirurgico getBloquequirurgico() {
		return this.bloquequirurgico;
	}

	public void setBloquequirurgico(BloqueQuirurgico bloquequirurgico) {
		this.bloquequirurgico = bloquequirurgico;
	}

	public DisponibilidadProfesional getDisponibilidadprofesional() {
		return this.disponibilidadprofesional;
	}

	public void setDisponibilidadprofesional(DisponibilidadProfesional disponibilidadprofesional) {
		this.disponibilidadprofesional = disponibilidadprofesional;
	}

}