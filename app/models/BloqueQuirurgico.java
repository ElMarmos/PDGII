package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bloquequirurgico database table.
 * 
 */
@Entity
@NamedQuery(name="BloqueQuirurgico.findAll", query="SELECT b FROM BloqueQuirurgico b")
public class BloqueQuirurgico extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idBloqueQuirurgico;

	private byte habilitado;

	private Timestamp horaFinal;

	private Timestamp horaInicio;

	private String nombreBloque;
	
	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	//bi-directional many-to-one association to BloqueDisponibilidad
	@OneToMany(mappedBy="bloquequirurgico")
	private List<BloqueDisponibilidad> bloquedisponibilidads;

	//bi-directional many-to-one association to Especialidad
	@ManyToOne
	@JoinColumn(name="idEspecialidad")
	private Especialidad especialidad;

	public BloqueQuirurgico() {
	}

	public int getIdBloqueQuirurgico() {
		return this.idBloqueQuirurgico;
	}

	public void setIdBloqueQuirurgico(int idBloqueQuirurgico) {
		this.idBloqueQuirurgico = idBloqueQuirurgico;
	}

	public byte getHabilitado() {
		return this.habilitado;
	}

	public void setHabilitado(byte habilitado) {
		this.habilitado = habilitado;
	}

	public Timestamp getHoraFinal() {
		return this.horaFinal;
	}

	public void setHoraFinal(Timestamp horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Timestamp getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getNombreBloque() {
		return this.nombreBloque;
	}

	public void setNombreBloque(String nombreBloque) {
		this.nombreBloque = nombreBloque;
	}

	public List<BloqueDisponibilidad> getBloquedisponibilidads() {
		return this.bloquedisponibilidads;
	}

	public void setBloquedisponibilidads(List<BloqueDisponibilidad> bloquedisponibilidads) {
		this.bloquedisponibilidads = bloquedisponibilidads;
	}

	public BloqueDisponibilidad addBloquedisponibilidad(BloqueDisponibilidad bloquedisponibilidad) {
		getBloquedisponibilidads().add(bloquedisponibilidad);
		bloquedisponibilidad.setBloquequirurgico(this);

		return bloquedisponibilidad;
	}

	public BloqueDisponibilidad removeBloquedisponibilidad(BloqueDisponibilidad bloquedisponibilidad) {
		getBloquedisponibilidads().remove(bloquedisponibilidad);
		bloquedisponibilidad.setBloquequirurgico(null);

		return bloquedisponibilidad;
	}

	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	
	

}