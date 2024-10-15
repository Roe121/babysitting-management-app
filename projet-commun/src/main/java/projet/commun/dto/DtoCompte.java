package projet.commun.dto;

import java.io.Serializable;


@SuppressWarnings("serial")
public class DtoCompte implements Serializable  {

	//-------
	// Champs
	//-------
	
	private int			id;
	
	private String		pseudo;
	
	private String		motDePasse;
	
	private boolean		flagAdmin;
	
	//-------
	// Constructeurs
	//-------
	
	public DtoCompte() {
	}

	public DtoCompte(int id, String pseudo, String motDePasse, boolean flagAdmin ) {
		this.id = id;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.flagAdmin = flagAdmin;
	}
	
	
	//-------
	// Getters & setters
	//-------

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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
}
