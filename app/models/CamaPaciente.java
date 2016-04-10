package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.sql.Timestamp;


/**
 * The persistent class for the camapaciente database table.
 * 
 */
@Entity
@NamedQuery(name="CamaPaciente.findAll", query="SELECT c FROM CamaPaciente c")
public class CamaPaciente extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CamaPacientePK id;

	private Timestamp fechaIngreso;

	private Timestamp fechaSalida;

	//bi-directional many-to-one association to Cama
	@ManyToOne
	@JoinColumn(name="idCama")
	private Cama cama;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="idPaciente")
	private Paciente paciente;

	public CamaPaciente() {
	}

	public CamaPacientePK getId() {
		return this.id;
	}

	public void setId(CamaPacientePK id) {
		this.id = id;
	}

	public Timestamp getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Timestamp getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(Timestamp fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Cama getCama() {
		return this.cama;
	}

	public void setCama(Cama cama) {
		this.cama = cama;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}