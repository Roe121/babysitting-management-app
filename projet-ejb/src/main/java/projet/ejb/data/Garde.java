package projet.ejb.data;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "garde")
public class Garde {
    
    //-------
    // Champs
    //-------

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idgarde")
    private int id; 

    @Column(name = "datejour")
    private Date dateJour;

    @Column(name = "heurearrivee")
    private LocalTime heureArrivee;

    @Column(name = "heuredepart")
    private LocalTime heureDepart;

    @Column(name = "aprisrepas")
    private boolean prisRepas;
    
    @ManyToOne
    @JoinColumn(name = "idcontrat") 
    private Contrat contrat;


    //-------
    // Constructeurs
    //-------

    public Garde() {
    }

    public Garde(int idGarde,Contrat contrat, Date dateJour, LocalTime heureArrivee, LocalTime heureDepart, boolean prisRepas) {
        this.id = idGarde;
        this.contrat = contrat;
        this.dateJour = dateJour;
        this.heureArrivee = heureArrivee;
        this.heureDepart = heureDepart;
        this.prisRepas = prisRepas;


    }
        
    //-------
    // Getters & setters
    //-------

    public int getId() { // Getter pour idGarde
        return id;
    }

    public void setId(int id) { // Setter pour idGarde
        this.id = id;
    }

    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public boolean isPrisRepas() {
        return prisRepas;
    }

    public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public void setPrisRepas(boolean prisRepas) {
        this.prisRepas = prisRepas;
    }

    //-------
    // equals() et hashcode()
    //-------

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id; // Utilisation de idGarde
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
        Garde other = (Garde) obj;
        if (id != other.id) // Utilisation de idGarde
            return false;
        return true;
    }
}
