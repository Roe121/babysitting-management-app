package projet.commun.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DtoTarif implements Serializable {

	// -------
	// Champs
	// -------

	private int id;

	private String typeTarif;

	private double montant;

	// -------
	// Constructeurs
	// -------

	public DtoTarif() {
	}

	public DtoTarif(int id, String typeTarif, double montant) {
		super();
		this.id = id;
		this.typeTarif = typeTarif;
		this.montant = montant;
	}

	// -------
	// Getters & setters
	//-------

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
}