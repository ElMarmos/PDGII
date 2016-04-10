package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the solicitud database table.
 * 
 */
@Entity
@NamedQuery(name="Solicitud.findAll", query="SELECT s FROM Solicitud s")
public class Solicitud extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idSolicitud;

	private Timestamp fechaAtencion;

	private Timestamp fechaCirugia;

	private Timestamp fechaProgramacion;

	private Timestamp fechaSolicitud;

	private String jornadaPreferencia;

	private String tipoPaciente;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="idPaciente")
	private Paciente paciente;

	//bi-directional many-to-many association to Procedimiento
	@ManyToMany
	@JoinTable(
		name="solicitudprocedimiento"
		, joinColumns={
			@JoinColumn(name="idSolicitud")
			}
		, inverseJoinColumns={
			@JoinColumn(name="codigoProcedimiento")
			}
		)
	private List<Procedimiento> procedimientos;

	public Solicitud() {
	}

	public int getIdSolicitud() {
		return this.idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Timestamp getFechaAtencion() {
		return this.fechaAtencion;
	}

	public void setFechaAtencion(Timestamp fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public Timestamp getFechaCirugia() {
		return this.fechaCirugia;
	}

	public void setFechaCirugia(Timestamp fechaCirugia) {
		this.fechaCirugia = fechaCirugia;
	}

	public Timestamp getFechaProgramacion() {
		return this.fechaProgramacion;
	}

	public void setFechaProgramacion(Timestamp fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}

	public Timestamp getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	public void setFechaSolicitud(Timestamp fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getJornadaPreferencia() {
		return this.jornadaPreferencia;
	}

	public void setJornadaPreferencia(String jornadaPreferencia) {
		this.jornadaPreferencia = jornadaPreferencia;
	}

	public String getTipoPaciente() {
		return this.tipoPaciente;
	}

	public void setTipoPaciente(String tipoPaciente) {
		this.tipoPaciente = tipoPaciente;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Procedimiento> getProcedimientos() {
		return this.procedimientos;
	}

	public void setProcedimientos(List<Procedimiento> procedimientos) {
		this.procedimientos = procedimientos;
	}

}