package projet.ejb.data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contrat")
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "idcontrat")
    private int id; 

    @ManyToOne // Relation ManyToOne avec Parent
    @JoinColumn(name = "idparent", nullable = false)
    private Parent parent; 
    
    @Column(name = "nomenfant", nullable = false)
    private String nomEnfant;

    @Column(name = "prenomenfant", nullable = false)
    private String prenomEnfant;

    @Column(name = "datenaissanceenfant", nullable = false)
    @Temporal(TemporalType.DATE) // Type de données pour les dates
    private Date dateNaissanceEnfant;

    @Column(name = "datedebut", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Column(name = "datefin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @Column(name = "tarifhoraire", nullable = false)
    private double tarifHoraire; // Utilisation de double pour les montants

    @Column(name = "indemniteentretientauxhoraire", nullable = false)
    private double indemniteEntretienTauxHoraire;

    @Column(name = "indemniteentretienminimum", nullable = false)
    private double indemniteEntretienMinimum;

    @Column(name = "indemniterepas")
    private double indemniteRepas;

    
    
    //______________________________________________________________________________________________________________//
    
	public Contrat() {
	}

    public Contrat(int id, Parent parent, String nomEnfant, String prenomEnfant, Date dateNaissanceEnfant,
			Date dateDebut, Date dateFin, double tarifHoraire, double indemniteEntretienTauxHoraire,
			double indemniteEntretienMinimum, double indemniteRepas) {
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

    
    
  //______________________________________________________________________________________________________________//
    
    
    
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public double getTarifHoraire() {
		return tarifHoraire;
	}

	public void setTarifHoraire(double tarifHoraire) {
		this.tarifHoraire = tarifHoraire;
	}

	public double getIndemniteEntretienTauxHoraire() {
		return indemniteEntretienTauxHoraire;
	}

	public void setIndemniteEntretienTauxHoraire(double indemniteEntretienTauxHoraire) {
		this.indemniteEntretienTauxHoraire = indemniteEntretienTauxHoraire;
	}

	public double getIndemniteEntretienMinimum() {
		return indemniteEntretienMinimum;
	}

	public void setIndemniteEntretienMinimum(double indemniteEntretienMinimum) {
		this.indemniteEntretienMinimum = indemniteEntretienMinimum;
	}

	public double getIndemniteRepas() {
		return indemniteRepas;
	}

	public void setIndemniteRepas(double indemniteRepas) {
		this.indemniteRepas = indemniteRepas;
	}

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
		Contrat other = (Contrat) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
}
