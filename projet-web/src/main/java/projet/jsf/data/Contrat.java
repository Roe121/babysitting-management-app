package projet.jsf.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.constraints.PastOrPresent;

@SuppressWarnings("serial")
public class Contrat implements Serializable {

    //------- Champs -------
    private Integer id;

    @NotNull(message = "Le parent doit être renseigné")
    private Parent parent;  // Utilisation de l'objet Parent directement

    @NotBlank(message = "Le nom de l'enfant doit être renseigné")
    @Size(max = 50, message = "Valeur trop longue pour le nom de l'enfant : 50 caractères maximum")
    private String nomEnfant;

    @NotBlank(message = "Le prénom de l'enfant doit être renseigné")
    @Size(max = 50, message = "Valeur trop longue pour le prénom de l'enfant : 50 caractères maximum")
    private String prenomEnfant;

    @NotNull(message = "La date de naissance de l'enfant doit être renseignée")
    @PastOrPresent(message = "La date de naissance doit être une date passée ou actuelle")
    private Date dateNaissanceEnfant;

    @NotNull(message = "La date de début du contrat doit être renseignée")
    private Date dateDebut;

    @NotNull(message = "La date de fin du contrat doit être renseignée")
    private Date dateFin;

    @NotNull(message = "Le tarif horaire doit être renseigné")
    @Positive(message = "Le tarif horaire doit être positif")
    private Double tarifHoraire;

    @NotNull(message = "Le taux horaire de l'indemnité d'entretien doit être renseigné")
    @Positive(message = "Le taux horaire de l'indemnité d'entretien doit être positif")
    private Double indemniteEntretienTauxHoraire;

    @NotNull(message = "Le montant minimum de l'indemnité d'entretien doit être renseigné")
    @Positive(message = "Le montant minimum de l'indemnité d'entretien doit être positif")
    private Double indemniteEntretienMinimum;

    private Double indemniteRepas; // Facultatif, donc sans validation obligatoire
    
    
    

    //------- Constructeurs -------
    public Contrat() {
    }

    public Contrat(Integer id, Parent parent, String nomEnfant, String prenomEnfant, Date dateNaissanceEnfant,
                   Date dateDebut, Date dateFin, Double tarifHoraire, Double indemniteEntretienTauxHoraire,
                   Double indemniteEntretienMinimum, Double indemniteRepas) {
        super();
        this.id = id;
        this.parent = parent;
        this.nomEnfant = nomEnfant;
        this.prenomEnfant = prenomEnfant;
        this.dateNaissanceEnfant = dateNaissanceEnfant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tarifHoraire = tarifHoraire;
        this.indemniteEntretienTauxHoraire = indemniteEntretienTauxHoraire;
        this.indemniteEntretienMinimum = indemniteEntretienMinimum;
        this.indemniteRepas = indemniteRepas;
    }

    //------- Getters & setters -------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getNomEnfant() {
        return nomEnfant;
    }

    public void setNomEnfant(String nomEnfant) {
        this.nomEnfant = nomEnfant;
    }

    public String getPrenomEnfant() {
        return prenomEnfant;
    }

    public void setPrenomEnfant(String prenomEnfant) {
        this.prenomEnfant = prenomEnfant;
    }

    public Date getDateNaissanceEnfant() {
        return dateNaissanceEnfant;
    }

    public void setDateNaissanceEnfant(Date dateNaissanceEnfant) {
        this.dateNaissanceEnfant = dateNaissanceEnfant;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Double getTarifHoraire() {
        return tarifHoraire;
    }

    public void setTarifHoraire(Double tarifHoraire) {
        this.tarifHoraire = tarifHoraire;
    }

    public Double getIndemniteEntretienTauxHoraire() {
        return indemniteEntretienTauxHoraire;
    }

    public void setIndemniteEntretienTauxHoraire(Double indemniteEntretienTauxHoraire) {
        this.indemniteEntretienTauxHoraire = indemniteEntretienTauxHoraire;
    }

    public Double getIndemniteEntretienMinimum() {
        return indemniteEntretienMinimum;
    }

    public void setIndemniteEntretienMinimum(Double indemniteEntretienMinimum) {
        this.indemniteEntretienMinimum = indemniteEntretienMinimum;
    }

    public Double getIndemniteRepas() {
        return indemniteRepas;
    }

    public void setIndemniteRepas(Double indemniteRepas) {
        this.indemniteRepas = indemniteRepas;
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
        Contrat other = (Contrat) obj;
        return Objects.equals(id, other.id);
    }

    //------- toString() (facultatif pour le débogage) -------
    @Override
    public String toString() {
        return "Contrat [id=" + id + ", nomEnfant=" + nomEnfant + ", prenomEnfant=" + prenomEnfant 
            + ", dateNaissanceEnfant=" + dateNaissanceEnfant + "]";
    }
    
}
