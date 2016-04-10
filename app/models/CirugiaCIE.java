package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.sql.Time;


/**
 * The persistent class for the cirugiacie database table.
 * 
 */
@Entity
@NamedQuery(name="CirugiaCIE.findAll", query="SELECT c FROM CirugiaCIE c")
public class CirugiaCIE extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CirugiaCIEPK id;

	private Time horaFin;

	private Time horaInicio;

	@Lob
	private String observacion;

	//bi-directional many-to-one association to CIE
	@ManyToOne
	@JoinColumn(name="idCIE")
	private CIE cie;

	//bi-directional many-to-one association to Cirugia
	@ManyToOne
	@JoinColumn(name="idCirugia")
	private Cirugia cirugia;

	public CirugiaCIE() {
	}

	public CirugiaCIEPK getId() {
		return this.id;
	}

	public void setId(CirugiaCIEPK id) {
		this.id = id;
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

	public CIE getCie() {
		return this.cie;
	}

	public void setCie(CIE cie) {
		this.cie = cie;
	}

	public Cirugia getCirugia() {
		return this.cirugia;
	}

	public void setCirugia(Cirugia cirugia) {
		this.cirugia = cirugia;
	}

}