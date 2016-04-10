package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.List;


/**
 * The persistent class for the cama database table.
 * 
 */
@Entity
@NamedQuery(name="Cama.findAll", query="SELECT c FROM Cama c")
public class Cama extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCama;

	private String codigo;

	private String estado;

	//bi-directional many-to-one association to CamaPaciente
	@OneToMany(mappedBy="cama")
	private List<CamaPaciente> camapacientes;

	public Cama() {
	}

	public int getIdCama() {
		return this.idCama;
	}

	public void setIdCama(int idCama) {
		this.idCama = idCama;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<CamaPaciente> getCamapacientes() {
		return this.camapacientes;
	}

	public void setCamapacientes(List<CamaPaciente> camapacientes) {
		this.camapacientes = camapacientes;
	}

	public CamaPaciente addCamapaciente(CamaPaciente camapaciente) {
		getCamapacientes().add(camapaciente);
		camapaciente.setCama(this);

		return camapaciente;
	}

	public CamaPaciente removeCamapaciente(CamaPaciente camapaciente) {
		getCamapacientes().remove(camapaciente);
		camapaciente.setCama(null);

		return camapaciente;
	}

}