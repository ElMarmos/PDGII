package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.jpa.GenericModel;


/**
 * The persistent class for the cirujano database table.
 * 
 */
@Entity
@NamedQuery(name="Cirujano.findAll", query="SELECT c FROM Cirujano c")
public class Cirujano extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCirujano;

	//bi-directional many-to-one association to ProfesionalSalud
	@OneToOne
	@JoinColumn(name="idProfesionalSalud")
	private ProfesionalSalud profesionalsalud;
	
	@OneToMany(mappedBy="cirujano")
	private List<Solicitud> solicitudes;

	public Cirujano(ProfesionalSalud profesionalSalud) {
		this.profesionalsalud = profesionalSalud;
		solicitudes = new ArrayList<>();
	}

	public int getIdCirujano() {
		return this.idCirujano;
	}

	public void setIdCirujano(int idCirujano) {
		this.idCirujano = idCirujano;
	}

	public ProfesionalSalud getProfesionalsalud() {
		return this.profesionalsalud;
	}

	public void setProfesionalsalud(ProfesionalSalud profesionalsalud) {
		this.profesionalsalud = profesionalsalud;
	}

	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}
	
	public Solicitud addSolicitud(Solicitud solicitud){
		getSolicitudes().add(solicitud);
		solicitud.setCirujano(this);
		return solicitud;
	}
	public Solicitud removeSolicitud(Solicitud solicitud){
		getSolicitudes().add(solicitud);
		solicitud.setCirujano(null);
		return solicitud;
	}
	
	

}