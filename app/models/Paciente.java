package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@NamedQuery(name="Paciente.findAll", query="SELECT p FROM Paciente p")
public class Paciente extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPaciente;

	private String apellidos;

	private String ciudadPrecediencia;

	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	private String nombres;

	private String numeroHistoriaClinica;

	private String numeroIdentificacion;

	private double peso;

	private double prioridad;

	private String sexo;

	private String talla;
	
	private String eps;

	private String tipoIdentificacion;

	//bi-directional many-to-one association to CamaPaciente
	@OneToMany(mappedBy="paciente")
	private List<CamaPaciente> camapacientes;

	//bi-directional many-to-one association to Cirugia
	@OneToMany(mappedBy="paciente")
	private List<Cirugia> cirugias;

	//bi-directional many-to-one association to Patientologia
	@OneToMany(mappedBy="paciente")
	private List<Patientologia> patientologias;

	//bi-directional many-to-one association to Seguimiento
	@OneToMany(mappedBy="paciente")
	private List<Seguimiento> seguimientos;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="paciente")
	private List<Solicitud> solicituds;

	public Paciente(){
		
	}
	
	public Paciente(String nombres, String apellidos, String tipoIdent, String numeroIdentificacion, Date fechaNacimiento, String sexo, double peso, String talla, double prioridad, String ciudadProcedencia, String eps, String numeroHistoria){
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tipoIdentificacion = tipoIdent;
		this.numeroIdentificacion = numeroIdentificacion;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.peso = peso;
		this.talla = talla;
		this.prioridad = prioridad;
		this.ciudadPrecediencia = ciudadProcedencia;
		this.eps = eps;
		this.numeroHistoriaClinica = numeroHistoria;
		
		patientologias = new ArrayList<>();
		camapacientes = new ArrayList<>();
		cirugias = new ArrayList<>();
		seguimientos = new ArrayList<>();
		solicituds = new ArrayList<>();
	}
	
	public String getEps() {
		return eps;
	}
	public void setEps(String eps) {
		this.eps = eps;
	}
	public int getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCiudadPrecediencia() {
		return this.ciudadPrecediencia;
	}

	public void setCiudadPrecediencia(String ciudadPrecediencia) {
		this.ciudadPrecediencia = ciudadPrecediencia;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNumeroHistoriaClinica() {
		return this.numeroHistoriaClinica;
	}

	public void setNumeroHistoriaClinica(String numeroHistoriaClinica) {
		this.numeroHistoriaClinica = numeroHistoriaClinica;
	}

	public String getNumeroIdentificacion() {
		return this.numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public double getPeso() {
		return this.peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(double prioridad) {
		this.prioridad = prioridad;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTalla() {
		return this.talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getTipoIdentificacion() {
		return this.tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public List<CamaPaciente> getCamapacientes() {
		return this.camapacientes;
	}

	public void setCamapacientes(List<CamaPaciente> camapacientes) {
		this.camapacientes = camapacientes;
	}

	public CamaPaciente addCamapaciente(CamaPaciente camapaciente) {
		getCamapacientes().add(camapaciente);
		camapaciente.setPaciente(this);

		return camapaciente;
	}

	public CamaPaciente removeCamapaciente(CamaPaciente camapaciente) {
		getCamapacientes().remove(camapaciente);
		camapaciente.setPaciente(null);

		return camapaciente;
	}

	public List<Cirugia> getCirugias() {
		return this.cirugias;
	}

	public void setCirugias(List<Cirugia> cirugias) {
		this.cirugias = cirugias;
	}

	public Cirugia addCirugia(Cirugia cirugia) {
		getCirugias().add(cirugia);
		cirugia.setPaciente(this);

		return cirugia;
	}

	public Cirugia removeCirugia(Cirugia cirugia) {
		getCirugias().remove(cirugia);
		cirugia.setPaciente(null);

		return cirugia;
	}

	public List<Patientologia> getPatientologias() {
		return this.patientologias;
	}

	public void setPatientologias(List<Patientologia> patientologias) {
		this.patientologias = patientologias;
	}

	public Patientologia addPatientologia(Patientologia patientologia) {
		getPatientologias().add(patientologia);
		patientologia.setPaciente(this);

		return patientologia;
	}

	public Patientologia removePatientologia(Patientologia patientologia) {
		getPatientologias().remove(patientologia);
		patientologia.setPaciente(null);

		return patientologia;
	}

	public List<Seguimiento> getSeguimientos() {
		return this.seguimientos;
	}

	public void setSeguimientos(List<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}

	public Seguimiento addSeguimiento(Seguimiento seguimiento) {
		getSeguimientos().add(seguimiento);
		seguimiento.setPaciente(this);

		return seguimiento;
	}

	public Seguimiento removeSeguimiento(Seguimiento seguimiento) {
		getSeguimientos().remove(seguimiento);
		seguimiento.setPaciente(null);

		return seguimiento;
	}

	public List<Solicitud> getSolicituds() {
		return this.solicituds;
	}

	public void setSolicituds(List<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}

	public Solicitud addSolicitud(Solicitud solicitud) {
		getSolicituds().add(solicitud);
		solicitud.setPaciente(this);

		return solicitud;
	}

	public Solicitud removeSolicitud(Solicitud solicitud) {
		getSolicituds().remove(solicitud);
		solicitud.setPaciente(null);

		return solicitud;
	}

}