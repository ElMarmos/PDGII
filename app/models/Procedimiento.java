package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.List;


/**
 * The persistent class for the procedimiento database table.
 * 
 */
@Entity
@NamedQuery(name="Procedimiento.findAll", query="SELECT p FROM Procedimiento p")
public class Procedimiento extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigoProcedimiento;

	@Lob
	private String procedimiento;

	//bi-directional many-to-one association to CirugiaProcedimiento
	@OneToMany(mappedBy="procedimiento")
	private List<CirugiaProcedimiento> cirugiaprocedimientos;

	//bi-directional many-to-one association to Especialidad
	@ManyToOne
	@JoinColumn(name="idEspecialidad")
	private Especialidad especialidad;

	//bi-directional many-to-many association to Solicitud
	@OneToMany(mappedBy="procedimiento")
	private List<SolicitudProcedimientos> solicitudProcedimientos;

	public Procedimiento() {
	}

	public int getCodigoProcedimiento() {
		return this.codigoProcedimiento;
	}

	public void setCodigoProcedimiento(int codigoProcedimiento) {
		this.codigoProcedimiento = codigoProcedimiento;
	}

	public String getProcedimiento() {
		return this.procedimiento;
	}

	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}

	public List<CirugiaProcedimiento> getCirugiaprocedimientos() {
		return this.cirugiaprocedimientos;
	}

	public void setCirugiaprocedimientos(List<CirugiaProcedimiento> cirugiaprocedimientos) {
		this.cirugiaprocedimientos = cirugiaprocedimientos;
	}

	public CirugiaProcedimiento addCirugiaprocedimiento(CirugiaProcedimiento cirugiaprocedimiento) {
		getCirugiaprocedimientos().add(cirugiaprocedimiento);
		cirugiaprocedimiento.setProcedimiento(this);

		return cirugiaprocedimiento;
	}

	public CirugiaProcedimiento removeCirugiaprocedimiento(CirugiaProcedimiento cirugiaprocedimiento) {
		getCirugiaprocedimientos().remove(cirugiaprocedimiento);
		cirugiaprocedimiento.setProcedimiento(null);

		return cirugiaprocedimiento;
	}

	public Especialidad getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public List<SolicitudProcedimientos> getSolicitudProcedimientos() {
		return solicitudProcedimientos;
	}

	public void setSolicitudProcedimientos(
			List<SolicitudProcedimientos> solicitudProcedimientos) {
		this.solicitudProcedimientos = solicitudProcedimientos;
	}
	
	public SolicitudProcedimientos addSolicitudProcedimientos(SolicitudProcedimientos solicitudProcedimientos){
		getSolicitudProcedimientos().add(solicitudProcedimientos);
		solicitudProcedimientos.setProcedimiento(this);
		return solicitudProcedimientos;
	}
	public SolicitudProcedimientos removeSolicitudProcedimientoes(SolicitudProcedimientos solicitudProcedimientos){
		getSolicitudProcedimientos().add(solicitudProcedimientos);
		solicitudProcedimientos.setProcedimiento(null);
		return solicitudProcedimientos;
	}
	
	

}