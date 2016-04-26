package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the cirugia database table.
 * 
 */
@Entity
@NamedQuery(name="Cirugia.findAll", query="SELECT c FROM Cirugia c")
public class Cirugia extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCirugia;

	private double duracion;

	private Timestamp fechaIngreso;

	private Timestamp horaCierre;

	private Timestamp inicioIncision;

	private String tipocirugia;
	
	private String estado;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="idPaciente")
	private Paciente paciente;

	//bi-directional many-to-one association to Quirofano
	@ManyToOne
	@JoinColumn(name="idQuirofano")
	private Quirofano quirofano;

	//bi-directional many-to-one association to CirugiaCIE
	@OneToMany(mappedBy="cirugia")
	private List<CirugiaCIE> cirugiacies;

	//bi-directional many-to-one association to CirugiaProcedimiento
	@OneToMany(mappedBy="cirugia")
	private List<CirugiaProcedimiento> cirugiaprocedimientos;

	//bi-directional many-to-one association to CirugiaProfesionalsalud
	@OneToMany(mappedBy="cirugia")
	private List<CirugiaProfesionalSalud> cirugiaprofesionalsaluds;

	//bi-directional many-to-one association to RecursoCirugia
	@OneToMany(mappedBy="cirugia")
	private List<RecursoCirugia> recursocirugias;

	public Cirugia() {
	}

	public int getIdCirugia() {
		return this.idCirugia;
	}

	public void setIdCirugia(int idCirugia) {
		this.idCirugia = idCirugia;
	}

	public double getDuracion() {
		return this.duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public Timestamp getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Timestamp getHoraCierre() {
		return this.horaCierre;
	}

	public void setHoraCierre(Timestamp horaCierre) {
		this.horaCierre = horaCierre;
	}

	public Timestamp getInicioIncision() {
		return this.inicioIncision;
	}

	public void setInicioIncision(Timestamp inicioIncision) {
		this.inicioIncision = inicioIncision;
	}

	public String getTipocirugia() {
		return this.tipocirugia;
	}

	public void setTipocirugia(String tipocirugia) {
		this.tipocirugia = tipocirugia;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Quirofano getQuirofano() {
		return this.quirofano;
	}

	public void setQuirofano(Quirofano quirofano) {
		this.quirofano = quirofano;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<CirugiaCIE> getCirugiacies() {
		return this.cirugiacies;
	}

	public void setCirugiacies(List<CirugiaCIE> cirugiacies) {
		this.cirugiacies = cirugiacies;
	}

	public CirugiaCIE addCirugiacy(CirugiaCIE cirugiacy) {
		getCirugiacies().add(cirugiacy);
		cirugiacy.setCirugia(this);

		return cirugiacy;
	}

	public CirugiaCIE removeCirugiacy(CirugiaCIE cirugiacy) {
		getCirugiacies().remove(cirugiacy);
		cirugiacy.setCirugia(null);

		return cirugiacy;
	}

	public List<CirugiaProcedimiento> getCirugiaprocedimientos() {
		return this.cirugiaprocedimientos;
	}

	public void setCirugiaprocedimientos(List<CirugiaProcedimiento> cirugiaprocedimientos) {
		this.cirugiaprocedimientos = cirugiaprocedimientos;
	}

	public CirugiaProcedimiento addCirugiaprocedimiento(CirugiaProcedimiento cirugiaprocedimiento) {
		getCirugiaprocedimientos().add(cirugiaprocedimiento);
		cirugiaprocedimiento.setCirugia(this);

		return cirugiaprocedimiento;
	}

	public CirugiaProcedimiento removeCirugiaprocedimiento(CirugiaProcedimiento cirugiaprocedimiento) {
		getCirugiaprocedimientos().remove(cirugiaprocedimiento);
		cirugiaprocedimiento.setCirugia(null);

		return cirugiaprocedimiento;
	}

	public List<CirugiaProfesionalSalud> getCirugiaprofesionalsaluds() {
		return this.cirugiaprofesionalsaluds;
	}

	public void setCirugiaprofesionalsaluds(List<CirugiaProfesionalSalud> cirugiaprofesionalsaluds) {
		this.cirugiaprofesionalsaluds = cirugiaprofesionalsaluds;
	}

	public CirugiaProfesionalSalud addCirugiaprofesionalsalud(CirugiaProfesionalSalud cirugiaprofesionalsalud) {
		getCirugiaprofesionalsaluds().add(cirugiaprofesionalsalud);
		cirugiaprofesionalsalud.setCirugia(this);

		return cirugiaprofesionalsalud;
	}

	public CirugiaProfesionalSalud removeCirugiaprofesionalsalud(CirugiaProfesionalSalud cirugiaprofesionalsalud) {
		getCirugiaprofesionalsaluds().remove(cirugiaprofesionalsalud);
		cirugiaprofesionalsalud.setCirugia(null);

		return cirugiaprofesionalsalud;
	}

	public List<RecursoCirugia> getRecursocirugias() {
		return this.recursocirugias;
	}

	public void setRecursocirugias(List<RecursoCirugia> recursocirugias) {
		this.recursocirugias = recursocirugias;
	}

	public RecursoCirugia addRecursocirugia(RecursoCirugia recursocirugia) {
		getRecursocirugias().add(recursocirugia);
		recursocirugia.setCirugia(this);

		return recursocirugia;
	}

	public RecursoCirugia removeRecursocirugia(RecursoCirugia recursocirugia) {
		getRecursocirugias().remove(recursocirugia);
		recursocirugia.setCirugia(null);

		return recursocirugia;
	}

}