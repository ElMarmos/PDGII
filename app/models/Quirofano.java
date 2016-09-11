package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.List;


/**
 * The persistent class for the quirofano database table.
 * 
 */
@Entity
@NamedQuery(name="Quirofano.findAll", query="SELECT q FROM Quirofano q")
public class Quirofano extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idQuirofano;

	private String nombreQuirofano;

	//bi-directional many-to-one association to Cirugia
	@OneToMany(mappedBy="quirofano")
	private List<Cirugia> cirugias;

	//bi-directional many-to-one association to Pabellon
	@ManyToOne
	@JoinColumn(name="idPabellon")
	private Pabellon pabellon;

	//bi-directional many-to-many association to Quirofano
	@OneToMany(mappedBy="quirofano")
	private List<QuirofanoDotacion> quirofanoDotaciones;
	
	//bi-directional many-to-many association to Quirofano
	@OneToMany(mappedBy="quirofano")
	private List<QuirofanoEspecialidad> quirofanoEspecialidades;
	
	//bi-directional many-to-one association to CamaPaciente
	@OneToMany(mappedBy="quirofano")
	private List<PlaneacionQuirofanos> planeacionesQuirofanos;

	public Quirofano() {
	}

	public int getIdQuirofano() {
		return this.idQuirofano;
	}

	public void setIdQuirofano(int idQuirofano) {
		this.idQuirofano = idQuirofano;
	}

	public String getNombreQuirofano() {
		return this.nombreQuirofano;
	}

	public void setNombreQuirofano(String nombreQuirofano) {
		this.nombreQuirofano = nombreQuirofano;
	}

	public List<Cirugia> getCirugias() {
		return this.cirugias;
	}

	public void setCirugias(List<Cirugia> cirugias) {
		this.cirugias = cirugias;
	}

	public Cirugia addCirugia(Cirugia cirugia) {
		getCirugias().add(cirugia);
		cirugia.setQuirofano(this);

		return cirugia;
	}

	public Cirugia removeCirugia(Cirugia cirugia) {
		getCirugias().remove(cirugia);
		cirugia.setQuirofano(null);

		return cirugia;
	}

	public Pabellon getPabellon() {
		return this.pabellon;
	}

	public void setPabellon(Pabellon pabellon) {
		this.pabellon = pabellon;
	}

	public List<QuirofanoDotacion> getQuirofanoDotaciones() {
		return quirofanoDotaciones;
	}

	public void setQuirofanoDotaciones(List<QuirofanoDotacion> quirofanoDotaciones) {
		this.quirofanoDotaciones = quirofanoDotaciones;
	}
	public QuirofanoDotacion addQuirofanoDotaciones(QuirofanoDotacion quirofanoDotacion){
		getQuirofanoDotaciones().add(quirofanoDotacion);
		quirofanoDotacion.setQuirofano(this);
		return quirofanoDotacion;
	}
	public QuirofanoDotacion removeQuirofanoDotaciones(QuirofanoDotacion quirofanoDotacion){
		getQuirofanoDotaciones().add(quirofanoDotacion);
		quirofanoDotacion.setQuirofano(null);
		return quirofanoDotacion;
	}

	public List<QuirofanoEspecialidad> getQuirofanoEspecialidades() {
		return quirofanoEspecialidades;
	}

	public void setQuirofanoEspecialidades(
			List<QuirofanoEspecialidad> quirofanoEspecialidades) {
		this.quirofanoEspecialidades = quirofanoEspecialidades;
	}
	public QuirofanoEspecialidad addQuirofanoEspecialidades(QuirofanoEspecialidad quirofanoEspecialidad){
		getQuirofanoEspecialidades().add(quirofanoEspecialidad);
		quirofanoEspecialidad.setQuirofano(this);
		return quirofanoEspecialidad;
	}
	public QuirofanoEspecialidad removeQuirofanoEspecialidades(QuirofanoEspecialidad quirofanoEspecialidad){
		getQuirofanoEspecialidades().add(quirofanoEspecialidad);
		quirofanoEspecialidad.setQuirofano(null);
		return quirofanoEspecialidad;
	}
	
	public List<PlaneacionQuirofanos> getPlaneacionesQuirofanos() {
		return planeacionesQuirofanos;
	}

	public void setPlaneacionesQuirofanos(
			List<PlaneacionQuirofanos> planeacionesQuirofanos) {
		this.planeacionesQuirofanos = planeacionesQuirofanos;
	}
	public PlaneacionQuirofanos addPlaneacionQuirofano(PlaneacionQuirofanos planeacionQuirofanos){
		getPlaneacionesQuirofanos().add(planeacionQuirofanos);
		planeacionQuirofanos.setQuirofano(this);
		return planeacionQuirofanos;
	}
	
	public PlaneacionQuirofanos removePlaneacionQuirofano(PlaneacionQuirofanos planeacionQuirofanos){
		getPlaneacionesQuirofanos().remove(planeacionQuirofanos);
		planeacionQuirofanos.setQuirofano(null);
		return planeacionQuirofanos;
	}
	
		

}