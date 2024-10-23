package projet.jsf.data;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
public class Tarif implements Serializable{

	private Integer		id;
	
	@NotBlank( message = "Le type de tarif doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le type de tarif : 25 car. maxi" )
	@Pattern(regexp = "Tarif Horaire Garde|Indemnité Entretien|Indemnité de Repas", 
    message = "Veuillez entrer une valeur valide : Tarif Horaire Garde, Indemnité Entretien, ou Indemnité de Repas.")
	private String		typeTarif;
	
	@NotNull
	@Positive
	private double    montant;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
