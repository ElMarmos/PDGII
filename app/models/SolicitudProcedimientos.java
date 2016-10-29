package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import java.sql.Time;


/**
 * The persistent class for the SolicitudProcedimientos database table.
 * 
 */
@Entity
@NamedQuery(name="SolicitudProcedimientos.findAll", query="SELECT s FROM SolicitudProcedimientos s")
public class SolicitudProcedimientos extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSolicitudProcedimiento;
	
	//bi-directional many-to-one association to Quirofano
	@ManyToOne
	@JoinColumn(name="idSolicitud")
	private Solicitud solicitud;

	//bi-directional many-to-one association to Dotacion
	@ManyToOne
	@JoinColumn(name="idProcedimiento")
	private Procedimiento procedimiento;

	public SolicitudProcedimientos(Solicitud solicitud, Procedimiento procedimiento) {
		this.solicitud = solicitud;
		this.procedimiento = procedimiento;
	}
	
	public int getIdSolicitudProcedimiento() {
		return idSolicitudProcedimiento;
	}

	public void setIdSolicitudProcedimiento(int idSolicitudProcedimiento) {
		this.idSolicitudProcedimiento = idSolicitudProcedimiento;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Procedimiento getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}
	
	

}