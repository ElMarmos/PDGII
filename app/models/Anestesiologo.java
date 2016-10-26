package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

/**
 * The persistent class for the anestesiologo database table.
 * 
 */
@Entity
@NamedQuery(name = "Anestesiologo.findAll", query = "SELECT a FROM Anestesiologo a")
public class Anestesiologo extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAnestesiologo;

	// bi-directional many-to-one association to ProfesionalSalud
	@OneToOne
	@JoinColumn(name = "idProfesionalSalud")
	private ProfesionalSalud profesionalsalud;

	public Anestesiologo(ProfesionalSalud profesionalSalud) {
		this.profesionalsalud = profesionalSalud;
	}

	public int getIdAnestesiologo() {
		return this.idAnestesiologo;
	}

	public void setIdAnestesiologo(int idAnestesiologo) {
		this.idAnestesiologo = idAnestesiologo;
	}

	public ProfesionalSalud getProfesionalsalud() {
		return this.profesionalsalud;
	}

	public void setProfesionalsalud(ProfesionalSalud profesionalsalud) {
		this.profesionalsalud = profesionalsalud;
	}

}