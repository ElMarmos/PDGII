package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the programacion database table.
 * 
 */
@Entity
@NamedQuery(name="Programacion.findAll", query="SELECT p FROM Programacion p")
public class Programacion extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idProgramacion;

	private Date fechaProgramacion;

	private Date fechaInicio;

	private Date fechaFin;
	
	private boolean principal;
	
	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="idPlaneacion")
	private Planeacion planeacion;
	
	//bi-directional many-to-one association to CirugiaProfesionalsalud
	@OneToMany(mappedBy="programacion")
	private List<Cirugia> programacionCirugias;

	//bi-directional many-to-one association to RecursoCirugia
	@OneToMany(mappedBy="programacion")
	private List<Indicador> programacionIndicadores;
	

	public Programacion() {
	}
	
	public int getIdProgramacion() {
		return idProgramacion;
	}
	public void setIdProgramacion(int idProgramacion) {
		this.idProgramacion = idProgramacion;
	}
	public Date getFechaProgramacion() {
		return fechaProgramacion;
	}
	public void setFechaProgramacion(Date fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public Planeacion getPlaneacion() {
		return planeacion;
	}

	public void setPlaneacion(Planeacion planeacion) {
		this.planeacion = planeacion;
	}

	public List<Cirugia> getProgramacionCirugias() {
		return programacionCirugias;
	}
	public void setProgramacionCirugias(List<Cirugia> programacionCirugias) {
		this.programacionCirugias = programacionCirugias;
	}
	public List<Indicador> getProgramacionIndicadores() {
		return programacionIndicadores;
	}
	public void setProgramacionIndicadores(List<Indicador> programacionIndicadores) {
		this.programacionIndicadores = programacionIndicadores;
	}
	public Cirugia addCirugia(Cirugia cirugia){
		getProgramacionCirugias().add(cirugia);
		cirugia.setProgramacion(this);
		return cirugia;
	}
	
	public Cirugia removeCirugia(Cirugia cirugia){
		getProgramacionCirugias().remove(cirugia);
		cirugia.setProgramacion(null);
		return cirugia;
	}
	
	public Indicador addIndicador(Indicador indicador){
		getProgramacionIndicadores().add(indicador);
		indicador.setProgramacion(this);
		return indicador;
	}
	public Indicador removeIndicador(Indicador indicador){
		getProgramacionIndicadores().remove(indicador);
		indicador.setProgramacion(null);
		return indicador;
	}
}