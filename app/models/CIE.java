package models;

import java.io.Serializable;

import javax.persistence.*;

import play.db.jpa.GenericModel;

import java.util.List;


/**
 * The persistent class for the cie database table.
 * 
 */
@Entity
@NamedQuery(name="CIE.findAll", query="SELECT c FROM CIE c")
public class CIE extends GenericModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCIE;

	private int codigo;

	@Lob
	private String procedimiento;

	//bi-directional many-to-one association to CirugiaCIE
	@OneToMany(mappedBy="cie")
	private List<CirugiaCIE> cirugiacies;

	public CIE() {
	}

	public int getIdCIE() {
		return this.idCIE;
	}

	public void setIdCIE(int idCIE) {
		this.idCIE = idCIE;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getProcedimiento() {
		return this.procedimiento;
	}

	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}

	public List<CirugiaCIE> getCirugiacies() {
		return this.cirugiacies;
	}

	public void setCirugiacies(List<CirugiaCIE> cirugiacies) {
		this.cirugiacies = cirugiacies;
	}

	public CirugiaCIE addCirugiacy(CirugiaCIE cirugiacy) {
		getCirugiacies().add(cirugiacy);
		cirugiacy.setCie(this);

		return cirugiacy;
	}

	public CirugiaCIE removeCirugiacy(CirugiaCIE cirugiacy) {
		getCirugiacies().remove(cirugiacy);
		cirugiacy.setCie(null);

		return cirugiacy;
	}

}