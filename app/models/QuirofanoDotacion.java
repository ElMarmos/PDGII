package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import java.sql.Time;


/**
 * The persistent class for the quirofanodotacion database table.
 * 
 */
@Entity
@NamedQuery(name="QuirofanoDotacion.findAll", query="SELECT q FROM QuirofanoDotacion q")
public class QuirofanoDotacion extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idQuirofanoDotacion;
	
	
	//bi-directional many-to-one association to Quirofano
	@ManyToOne
	@JoinColumn(name="idQuirofano")
	private Quirofano quirofano;

	//bi-directional many-to-one association to Dotacion
	@ManyToOne
	@JoinColumn(name="idDotacion")
	private Dotacion dotacion;

	public QuirofanoDotacion() {
	}

	public int getIdQuirofanoDotacion() {
		return idQuirofanoDotacion;
	}

	public void setIdQuirofanoDotacion(int idQuirofanoDotacion) {
		this.idQuirofanoDotacion = idQuirofanoDotacion;
	}

	public Quirofano getQuirofano() {
		return quirofano;
	}

	public void setQuirofano(Quirofano quirofano) {
		this.quirofano = quirofano;
	}

	public Dotacion getDotacion() {
		return dotacion;
	}

	public void setDotacion(Dotacion dotacion) {
		this.dotacion = dotacion;
	}
	
	

}