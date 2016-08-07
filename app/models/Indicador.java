package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the indicador database table.
 * 
 */
@Entity
@NamedQuery(name="Indicador.findAll", query="SELECT i FROM Indicador i")
public class Indicador extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idIndicador;

	private String nombre;

	private double valor;
	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="idProgramacion")
	private Programacion programacion;

	//bi-directional many-to-one association to Quirofano
	@ManyToOne
	@JoinColumn(name="idQuirofano")
	private Quirofano quirofano;

	
	public Indicador() {
	}
	
	
	public int getIdIndicador() {
		return idIndicador;
	}
	public void setIdIndicador(int idIndicador) {
		this.idIndicador = idIndicador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Programacion getProgramacion() {
		return programacion;
	}
	public void setProgramacion(Programacion programacion) {
		this.programacion = programacion;
	}
	public Quirofano getQuirofano() {
		return quirofano;
	}
	public void setQuirofano(Quirofano quirofano) {
		this.quirofano = quirofano;
	}
}