package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import java.sql.Time;


/**
 * The persistent class for the cirugiaprocedimiento database table.
 * 
 */
@Entity
@NamedQuery(name="CirugiaProcedimiento.findAll", query="SELECT c FROM CirugiaProcedimiento c")
public class CirugiaProcedimiento extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int idCirugiaProcedimiento;
	
	private Time horaFin;

	private Time horaInicio;

	@Lob
	private String observacion;

	//bi-directional many-to-one association to Cirugia
	@ManyToOne
	@JoinColumn(name="idCirugia")
	private Cirugia cirugia;

	//bi-directional many-to-one association to Procedimiento
	@ManyToOne
	@JoinColumn(name="codigoProcedimiento")
	private Procedimiento procedimiento;

	public CirugiaProcedimiento() {
	}

	public int getIdCirugiaProcedimiento() {
		return idCirugiaProcedimiento;
	}

	public void setIdCirugiaProcedimiento(int idCirugiaProcedimiento) {
		this.idCirugiaProcedimiento = idCirugiaProcedimiento;
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

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Cirugia getCirugia() {
		return this.cirugia;
	}

	public void setCirugia(Cirugia cirugia) {
		this.cirugia = cirugia;
	}

	public Procedimiento getProcedimiento() {
		return this.procedimiento;
	}

	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}

}