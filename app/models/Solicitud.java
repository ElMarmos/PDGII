package models;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import play.db.jpa.GenericModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Expose
	private int idSolicitud;
	@Expose
	private Timestamp fechaAtencion;
	@Expose
	private Timestamp fechaCirugia;
	@Expose
	private Timestamp fechaProgramacion;
	@Expose
	private Timestamp fechaSolicitud;
	@Expose
	private String jornadaPreferencia;
	@Expose
	private String tipoPaciente;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="idPaciente")
	@Expose
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name="idCirujano")
	private Cirujano cirujano;
	
	
	//bi-directional many-to-many association to SolicitudProcedimientos
	@OneToMany(mappedBy="solicitud")
	private List<SolicitudProcedimientos> solicitudProcedimientos;

	public Solicitud(Date fechaAtencion, Date fechaCirugia, Date fechaProgramacion, Date fechaSolicitud, String jornadaPreferencia, String tipoPaciente, Paciente paciente, Cirujano cirujano) {
		this.fechaAtencion = new Timestamp(fechaAtencion.getTime());
		this.fechaCirugia = new Timestamp(fechaCirugia.getTime());
		this.fechaProgramacion = new Timestamp(fechaProgramacion.getTime());
		this.fechaSolicitud = new Timestamp(fechaSolicitud.getTime());
		this.jornadaPreferencia = jornadaPreferencia;
		this.tipoPaciente = tipoPaciente;
		this.paciente = paciente;
		this.cirujano = cirujano;
		
		this.solicitudProcedimientos = new ArrayList<>();
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

	public List<SolicitudProcedimientos> getSolicitudProcedimientos() {
		return solicitudProcedimientos;
	}

	public void setSolicitudProcedimientos(
			List<SolicitudProcedimientos> solicitudProcedimientos) {
		this.solicitudProcedimientos = solicitudProcedimientos;
	}
	
	public SolicitudProcedimientos addSolicitudProcedimientos(SolicitudProcedimientos solicitudProcedimientos){
		getSolicitudProcedimientos().add(solicitudProcedimientos);
		solicitudProcedimientos.setSolicitud(this);
		return solicitudProcedimientos;
	}
	public SolicitudProcedimientos removeSolicitudProcedimientoes(SolicitudProcedimientos solicitudProcedimientos){
		getSolicitudProcedimientos().add(solicitudProcedimientos);
		solicitudProcedimientos.setSolicitud(null);
		return solicitudProcedimientos;
	}

	public Cirujano getCirujano() {
		return cirujano;
	}

	public void setCirujano(Cirujano cirujano) {
		this.cirujano = cirujano;
	}
	
	public List<Especialidad> getEspecialidades(){
		List<Especialidad> especialidades  = new ArrayList<Especialidad>();
		
		for (SolicitudProcedimientos soliPro : solicitudProcedimientos) {
			Especialidad espe = soliPro.getProcedimiento().getEspecialidad();
			boolean esta = false;
			for(int i = 0; i < especialidades.size() && !esta; i++){
				if(espe.getIdEspecialidad() == especialidades.get(i).getIdEspecialidad()){
					esta = true;
				}
			}
			if(!esta){
				especialidades.add(espe);
			}
		}
		
		return especialidades;
	}
	

	

}