package projet.jsf.data;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@SuppressWarnings("serial")
public class Parent implements Serializable {

    //-------
    // Champs
    //-------

    private Integer id;
    
	private Compte compte;

	@NotBlank(message = "Le nom doit être renseigné")
    @Size(max = 50, message = "Valeur trop longue pour le nom : 50 caractères maximum")
    private String nom;

    @NotBlank(message = "Le prénom doit être renseigné")
    @Size(max = 50, message = "Valeur trop longue pour le prénom : 50 caractères maximum")
    private String prenom;

    @NotBlank(message = "L'adresse postale doit être renseignée")
    @Size(max = 255, message = "Valeur trop longue pour l'adresse postale : 255 caractères maximum")
    private String adressePostale;

    @NotBlank(message = "L'adresse e-mail doit être renseignée")
    @Size(max = 255, message = "Valeur trop longue pour l'adresse e-mail : 255 caractères maximum")
    @Email(message = "Adresse e-mail invalide")
    private String adresseEmail;

    @NotBlank(message = "Le numéro de téléphone doit être renseigné")
    @Size(max = 15, message = "Valeur trop longue pour le numéro de téléphone : 15 caractères maximum")
    @Pattern(regexp = "\\d{10,15}", message = "Numéro de téléphone invalide (doit contenir entre 10 et 15 chiffres)")
    private String telephone;

    //-------
    // Constructeurs
    //-------

    public Parent() {
    }

    public Parent(Integer id, String nom, String prenom, String adressePostale, String adresseEmail, String telephone, Compte compte) {
        super();
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
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
        Parent other = (Parent) obj;
        return Objects.equals(id, other.id);
    }
}
