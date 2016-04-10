package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.sql.Timestamp;


/**
 * The persistent class for the seguimiento database table.
 * 
 */
@Entity
@NamedQuery(name="Seguimiento.findAll", query="SELECT s FROM Seguimiento s")
public class Seguimiento extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SeguimientoPK id;

	private Timestamp fechaCambio;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="idEstado")
	private Estado estado;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="idPaciente")
	private Paciente paciente;

	public Seguimiento() {
	}

	public SeguimientoPK getId() {
		return this.id;
	}

	public void setId(SeguimientoPK id) {
		this.id = id;
	}

	public Timestamp getFechaCambio() {
		return this.fechaCambio;
	}

	public void setFechaCambio(Timestamp fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}