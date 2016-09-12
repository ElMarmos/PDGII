package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idBloqueDisponibilidad;
	
	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	private Time horaFin;

	private Time horaInicio;
	
	private String dias;

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

	public int getIdBloqueDisponibilidad() {
		return idBloqueDisponibilidad;
	}
	public void setIdBloqueDisponibilidad(int idBloqueDisponibilidad) {
		this.idBloqueDisponibilidad = idBloqueDisponibilidad;
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
	
	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

}