package projet.jsf.data;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
public class Compte implements Serializable  {

	//-------
	// Champs
	//-------
	
	private Integer		id;
	
	@NotBlank( message = "Le pseudo doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le pseuo : 25 car. maxi" )
	private String		pseudo;

	@NotBlank( message = "Le mot de passe doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le mot de passe : 25 car. maxi" )
	private String		motDePasse;
	
	private boolean	flagAdmin;

	//-------
	// Constructeurs
	//-------
	
	public Compte() {
	}
	
	public Compte(Integer id, String pseudo, String motDePasse, boolean flagAdmin) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.flagAdmin = flagAdmin;
	}

	//-------
	// Getters & setters
	//-------

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	
	public boolean isFlagAdmin() {
		return flagAdmin;
	}
	
	public void setFlagAdmin(boolean flagAdmin) {
		this.flagAdmin = flagAdmin;
	}

	//-------
	// hashCode() & equals()
	//-------

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		var other = (Compte) obj;
		return Objects.equals(id, other.id);
	}
	
}
