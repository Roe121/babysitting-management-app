package projet.commun.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DtoParent implements Serializable {

    //-------
    // Champs
    //-------

    private int id;
    
    private DtoCompte compte;

    private String nom;

    private String prenom;

	private String adressePostale;

    private String adresseEmail;

    private String telephone;
    

    //-------
    // Constructeurs
    //-------

    public DtoParent() {
    }

    public DtoParent(int id, String nom, String prenom, String adressePostale, String adresseEmail, String telephone, DtoCompte compte) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adressePostale = adressePostale;
        this.adresseEmail = adresseEmail;
        this.telephone = telephone;
        this.compte = compte;
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
    
    public DtoCompte getCompte() {
		return compte;
	}

	public void setCompte(DtoCompte compte) {
		this.compte = compte;
	}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
