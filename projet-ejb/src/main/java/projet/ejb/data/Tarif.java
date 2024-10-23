package projet.ejb.data;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table( name = "tarif" )
public class Tarif  {
	
	//-------
	// Champs
	//-------

	@Id
	@GeneratedValue( strategy = IDENTITY)
	@Column( name = "idTarif")
	private int			id;
	
	@Column( name = "typeTarif")
	private String		typeTarif;
	
	@Column( name = "Montant")
	private double		montant;
	
	//-------
	// Constructeurs
	//-------
	
	public Tarif() {
	}
	
	public Tarif(int id, String typeTarif, double montant) {
		super();
		this.id = id;
		this.typeTarif = typeTarif;
		this.montant = montant;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeTarif() {
		return typeTarif;
	}

	public void setTypeTarif(String typeTarif) {
		this.typeTarif = typeTarif;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	//-------
	// equals() et hashcode()
	//-------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarif other = (Tarif) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
