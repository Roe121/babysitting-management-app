package projet.jsf.data;

import java.io.Serializable;
import java.time.LocalTime; // Pour gérer les heures
import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
public class Garde implements Serializable {

    //------- Champs -------
    private Integer id;

    @NotNull(message = "Le contrat doit être renseigné")
    private Contrat contrat;  // Utilisation de l'objet Contrat directement

    @NotNull(message = "La date du garde doit être renseignée")
    private Date dateJour; // Date de la garde

    @NotNull(message = "L'heure d'arrivée doit être renseignée")
    private LocalTime heureArrivee; // Heure d'arrivée

    @NotNull(message = "L'heure de départ doit être renseignée")
    private LocalTime heureDepart; // Heure de départ

    private boolean prisRepas; // Indique si un repas a été pris

    //------- Constructeurs -------
    public Garde() {
    }

    public Garde(Integer id, Contrat contrat, Date dateJour, LocalTime heureArrivee,
                 LocalTime heureDepart, boolean prisRepas) {
        this.id = id;
        this.contrat = contrat;
        this.dateJour = dateJour;
        this.heureArrivee = heureArrivee;
        this.heureDepart = heureDepart;
        this.prisRepas = prisRepas;
    }

    //------- Getters & setters -------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
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

    public void setPrisRepas(boolean prisRepas) {
        this.prisRepas = prisRepas;
    }

    //------- hashCode() & equals() -------
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
        Garde other = (Garde) obj;
        return Objects.equals(id, other.id);
    }

    //------- toString() (facultatif pour le débogage) -------
    @Override
    public String toString() {
        return "Garde [id=" + id + ", dateJour=" + dateJour + ", heureArrivee=" + heureArrivee + 
                ", heureDepart=" + heureDepart + ", aPrisRepas=" + prisRepas + "]";
    }
}
