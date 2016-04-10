package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.List;


/**
 * The persistent class for the especialidad database table.
 * 
 */
@Entity
@NamedQuery(name="Especialidad.findAll", query="SELECT e FROM Especialidad e")
public class Especialidad extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idEspecialidad;

	private String nombreEspecialidad;

	//bi-directional many-to-one association to BloqueQuirurgico
	@OneToMany(mappedBy="especialidad")
	private List<BloqueQuirurgico> bloquequirurgicos;

	//bi-directional many-to-one association to Procedimiento
	@OneToMany(mappedBy="especialidad")
	private List<Procedimiento> procedimientos;

	//bi-directional many-to-one association to ProfesionalSaludEspecialidad
	@OneToMany(mappedBy="especialidad")
	private List<ProfesionalSaludEspecialidad> profesionalsaludespecialidads;

	public Especialidad() {
	}

	public int getIdEspecialidad() {
		return this.idEspecialidad;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombreEspecialidad() {
		return this.nombreEspecialidad;
	}

	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}

	public List<BloqueQuirurgico> getBloquequirurgicos() {
		return this.bloquequirurgicos;
	}

	public void setBloquequirurgicos(List<BloqueQuirurgico> bloquequirurgicos) {
		this.bloquequirurgicos = bloquequirurgicos;
	}

	public BloqueQuirurgico addBloquequirurgico(BloqueQuirurgico bloquequirurgico) {
		getBloquequirurgicos().add(bloquequirurgico);
		bloquequirurgico.setEspecialidad(this);

		return bloquequirurgico;
	}

	public BloqueQuirurgico removeBloquequirurgico(BloqueQuirurgico bloquequirurgico) {
		getBloquequirurgicos().remove(bloquequirurgico);
		bloquequirurgico.setEspecialidad(null);

		return bloquequirurgico;
	}

	public List<Procedimiento> getProcedimientos() {
		return this.procedimientos;
	}

	public void setProcedimientos(List<Procedimiento> procedimientos) {
		this.procedimientos = procedimientos;
	}

	public Procedimiento addProcedimiento(Procedimiento procedimiento) {
		getProcedimientos().add(procedimiento);
		procedimiento.setEspecialidad(this);

		return procedimiento;
	}

	public Procedimiento removeProcedimiento(Procedimiento procedimiento) {
		getProcedimientos().remove(procedimiento);
		procedimiento.setEspecialidad(null);

		return procedimiento;
	}

	public List<ProfesionalSaludEspecialidad> getProfesionalsaludespecialidads() {
		return this.profesionalsaludespecialidads;
	}

	public void setProfesionalsaludespecialidads(List<ProfesionalSaludEspecialidad> profesionalsaludespecialidads) {
		this.profesionalsaludespecialidads = profesionalsaludespecialidads;
	}

	public ProfesionalSaludEspecialidad addProfesionalsaludespecialidad(ProfesionalSaludEspecialidad profesionalsaludespecialidad) {
		getProfesionalsaludespecialidads().add(profesionalsaludespecialidad);
		profesionalsaludespecialidad.setEspecialidad(this);

		return profesionalsaludespecialidad;
	}

	public ProfesionalSaludEspecialidad removeProfesionalsaludespecialidad(ProfesionalSaludEspecialidad profesionalsaludespecialidad) {
		getProfesionalsaludespecialidads().remove(profesionalsaludespecialidad);
		profesionalsaludespecialidad.setEspecialidad(null);

		return profesionalsaludespecialidad;
	}

}