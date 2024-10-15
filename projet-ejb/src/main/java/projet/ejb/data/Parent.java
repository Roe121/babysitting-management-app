package projet.ejb.data;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parent")
public class Parent {

    //-------
    // Champs
    //-------

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idparent")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adressepostale")
    private String adressePostale;

    @Column(name = "adresseemail")
    private String adresseEmail;

    @Column(name = "telephone")
    private String telephone;
    
    @OneToOne
    @JoinColumn(name = "idcompte", unique = true, nullable = false)  
    private Compte compte;

    //-------
    // Constructeurs
    //-------

    public Parent() {
    }

	public Parent(int id, String nom, String prenom, String adressePostale, String adresseEmail, String telephone, Compte compte) {
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
        Parent other = (Parent) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
