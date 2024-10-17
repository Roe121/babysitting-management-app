package projet.commun.dto;

import java.io.Serializable;
import java.util.Date;

public class DtoContrat implements Serializable {

    private static final long serialVersionUID = 1L;

    // Attributs correspondant à la table "contrat"
    private int id;
    private DtoParent parent;
    private String nomEnfant;
    private String prenomEnfant;
    private Date dateNaissanceEnfant;
    private Date dateDebut;
    private Date dateFin;
    private Double tarifHoraire;
    private Double indemniteEntretienTauxHoraire;
    private Double indemniteEntretienMinimum;
    private Double indemniteRepas;

    // Constructeur par défaut
    public DtoContrat() {
    }

    // Constructeur avec tous les attributs
    public DtoContrat(int id, DtoParent parent, String nomEnfant, String prenomEnfant, Date dateNaissanceEnfant, 
                      Date dateDebut, Date dateFin, Double tarifHoraire, Double indemniteEntretienTauxHoraire,
                      Double indemniteEntretienMinimum, Double indemniteRepas) {
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

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DtoParent getParent() {
        return parent;
    }

    public void setParent(DtoParent parent) {
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

    // Optionnel: méthode toString() pour faciliter le débogage
    @Override
    public String toString() {
        return "DtoContrat [id=" + id + ", nomEnfant=" + nomEnfant + ", prenomEnfant=" + prenomEnfant + 
                ", dateNaissanceEnfant=" + dateNaissanceEnfant + ", dateDebut=" + dateDebut + 
                ", dateFin=" + dateFin + ", tarifHoraire=" + tarifHoraire + "]";
    }
}
