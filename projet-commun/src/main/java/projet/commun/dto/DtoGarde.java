package projet.commun.dto;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

public class DtoGarde implements Serializable {

    private static final long serialVersionUID = 1L;

    // Attributs correspondant à la table "garde"
    private int id;
    private DtoContrat contrat;
    private Date dateJour;
    private LocalTime  heureArrivee;
    private LocalTime  heureDepart;
    private boolean prisRepas;

    // Constructeur par défaut
    public DtoGarde() {
    }

    // Constructeur avec tous les attributs
    public DtoGarde(int id, DtoContrat contrat, Date dateJour, LocalTime  heureArrivee, LocalTime  heureDepart, boolean prisRepas) {
        this.id = id;
        this.contrat = contrat;
        this.dateJour = dateJour;
        this.heureArrivee = heureArrivee;
        this.heureDepart = heureDepart;
        this.prisRepas = prisRepas;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DtoContrat getContrat() {
        return contrat;
    }

    public void setContrat(DtoContrat contrat) {
        this.contrat = contrat;
    }

    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    public LocalTime  getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime  heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public LocalTime  getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime  heureDepart) {
        this.heureDepart = heureDepart;
    }

    public boolean isPrisRepas() {
        return prisRepas;
    }

    public void setPrisRepas(boolean prisRepas) {
        this.prisRepas = prisRepas;
    }

    // Optionnel: méthode toString() pour faciliter le débogage
    @Override
    public String toString() {
        return "DtoGarde [idGarde=" + id + ", Contrat=" + contrat + ", dateJour=" + dateJour + 
                ", heureArrivee=" + heureArrivee + ", heureDepart=" + heureDepart + ", prisRepas=" + prisRepas + "]";
    }
}
