package projet.jsf.model.standard;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projet.commun.dto.DtoGarde;
import projet.commun.service.IServiceContrat;
import projet.commun.service.IServiceGarde;
import projet.jsf.data.Contrat;
import projet.jsf.data.Garde;
import projet.jsf.data.mapper.IMapper;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelStatistiques implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ModelParent.class);

    private List<String> labelsJournaliers; // Les étiquettes du graphique (dates) pour le premier chart
    private List<Number> remunerationsJournalières; // Les rémunérations journalières
    private List<String> labelsMensuels; // Les étiquettes du graphique (mois) pour le deuxième chart
    private List<Number> remunerationsMensuelles; // Les rémunérations mensuelles
    private List<Number> joursTravaillésMensuels;
    private List<Number> rmjt;

    @EJB
    private IServiceContrat serviceContrat;

    @EJB
    private IServiceGarde serviceGarde;

    @Inject
    private IMapper mapper;
    
    @PostConstruct
    public void init() {
        labelsJournaliers = new ArrayList<>();
        remunerationsJournalières = new ArrayList<>();
        labelsMensuels = new ArrayList<>();
        remunerationsMensuelles = new ArrayList<>();
        joursTravaillésMensuels = new ArrayList<>();
        rmjt = new ArrayList<>();
        
        getRemunerationsJournalières(); // Calcul des rémunérations par jour
        getRemunerationsMensuelles(); // Calcul des rémunérations par mois
       
        
    }
    
    public List<Number> getJoursTravaillésMensuels() {
        return joursTravaillésMensuels;
    }
    
    public List<Number> getRmjt() {
        return rmjt;
    }
    

    private double renumParJour(Date date) {
        double remunerationTotale = 0.0;

        List<Garde> gardesParDate = new ArrayList<>();
        for (DtoGarde dto : serviceGarde.listerParDate(date)) {
            gardesParDate.add(mapper.map(dto));
        }
        for (Garde garde : gardesParDate) {
            long heuresTravaillées = calculerHeuresTravaillees(garde.getHeureArrivee(), garde.getHeureDepart());
            double remuneration = calculerRemuneration(garde.getContrat(), heuresTravaillées, garde.isPrisRepas());
            remunerationTotale += remuneration;
        }

        return remunerationTotale; 
    }

    private double renumParMois(Date date) {
        double remunerationTotale = 0.0;
        int jourTr = 0;
        
        // Obtenir le premier jour du mois
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date premierJourDuMois = calendar.getTime();
        
        // Obtenir le dernier jour du mois
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date dernierJourDuMois = calendar.getTime();
        
        // Calculer la rémunération pour chaque jour de ce mois
        for (Date jour = premierJourDuMois; !jour.after(dernierJourDuMois); jour = ajouterUnJour(jour)) {
            remunerationTotale += renumParJour(jour); // Ajouter la rémunération du jour
            if(renumParJour(jour) !=0) {
            	jourTr++;
            }
        }
        
        if (jourTr != 0) {
            double remunerationMoyenne = remunerationTotale / jourTr;
            rmjt.add(remunerationMoyenne);
            logger.info("Le rmjt pour le mois {} est {}", premierJourDuMois, remunerationMoyenne);
        } else {
            rmjt.add(0);  // Aucun jour travaillé, donc ajout de 0
            logger.info("Le rmjt pour le mois {} est 0 (aucun jour travaillé)", premierJourDuMois);
        }
        joursTravaillésMensuels.add(jourTr);
        return remunerationTotale;
    }
    

    private Date ajouterUnJour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    private long calculerHeuresTravaillees(LocalTime heureArrivee, LocalTime heureDepart) {
        return (heureDepart.toSecondOfDay() - heureArrivee.toSecondOfDay()) / 3600; // Retourne les heures
    }

    private double calculerRemuneration(Contrat contrat, long heuresTravaillées, boolean aPrisRepas) {
        double remunerationGarde = heuresTravaillées * contrat.getTarifHoraire();
        double indemniteEntretien = Math.max(contrat.getIndemniteEntretienTauxHoraire() * heuresTravaillées,
                                              contrat.getIndemniteEntretienMinimum());
        double indemniteRepas = aPrisRepas ? contrat.getIndemniteRepas() : 0;

        return remunerationGarde + indemniteEntretien + indemniteRepas;
    }

    public List<String> getLabelsJournaliers() {
        List<String> labelsWithQuotes = new ArrayList<>();
        for (String label : labelsJournaliers) {
            labelsWithQuotes.add("'" + label + "'"); 
        }
        logger.info("La liste des labels journaliers est {}", labelsJournaliers);
        return labelsWithQuotes; 
    }

    public List<Number> getRemunerationsJournalières() {
        // Vider les listes pour recalculer à chaque appel
        remunerationsJournalières.clear();
        labelsJournaliers.clear();
        
        // Obtenir la date actuelle
        Calendar calendar = Calendar.getInstance();
        
        // Parcourir les 30 derniers jours
        for (int i = 0; i < 30; i++) {
            // Calculer la date courante (de i jours en arrière)
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date date = calendar.getTime();

            // Formater la date pour l'ajouter aux labels
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateLabel = sdf.format(date);
            labelsJournaliers.add(dateLabel);  // Ajouter le label formaté

            // Calculer la rémunération pour ce jour
            double remuneration = renumParJour(date);
            remunerationsJournalières.add(remuneration);  // Ajouter la rémunération à la liste
        }

        // Log des informations
//        logger.info("Les labels des 30 derniers jours: {}", labelsJournaliers);
//        logger.info("Les rémunérations des 30 derniers jours: {}", remunerationsJournalières);

        return remunerationsJournalières;
    }

    public List<String> getLabelsMensuels() {
        List<String> labelsWithQuotes = new ArrayList<>();
        for (String label : labelsMensuels) {
            labelsWithQuotes.add("'" + label + "'"); 
        }
//        logger.info("La liste des labels mensuels est {}", labelsMensuels);
        return labelsWithQuotes; 
    }

    public List<Number> getRemunerationsMensuelles() {
        // Vider les listes pour recalculer à chaque appel
        remunerationsMensuelles.clear();
        labelsMensuels.clear();
        joursTravaillésMensuels.clear();
        rmjt.clear();
        
        // Obtenir la date actuelle
        Calendar calendar = Calendar.getInstance();
        
        // Parcourir les 12 derniers mois
        for (int i = 0; i < 12; i++) {
            // Calculer la date du mois courant (i mois en arrière)
            calendar.add(Calendar.MONTH, -1);
            Date date = calendar.getTime();

            // Formater le mois et l'année pour l'ajouter aux labels
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String dateLabel = sdf.format(date);
            labelsMensuels.add(dateLabel);  // Ajouter le label formaté

            // Calculer la rémunération pour ce mois
            double remuneration = renumParMois(date);
            remunerationsMensuelles.add(remuneration);  // Ajouter la rémunération à la liste
        }

        // Log des informations
//        logger.info("Les labels des 12 derniers mois: {}", labelsMensuels);
        logger.info("le mrjt des 12 derniers mois: {}", rmjt);

        return remunerationsMensuelles;
    }
    
    public double calculerMontantAPayer(Contrat contrat) {
        double montantTotal = 0.0;
        
        List<Garde> gardesParContrat = new ArrayList<>();
        for (DtoGarde dto : serviceGarde.listerParContrat(contrat.getId())) {
            Garde garde = mapper.map(dto);
            
            // Ne conserver que les gardes du mois courant
            if (estDansMoisCourant(garde.getDateJour())) {
                gardesParContrat.add(garde);
            }
        }
        
        for (Garde garde : gardesParContrat) {
            long heuresTravaillées = calculerHeuresTravaillees(garde.getHeureArrivee(), garde.getHeureDepart());
            double remuneration = calculerRemuneration(garde.getContrat(), heuresTravaillées, garde.isPrisRepas());
            montantTotal += remuneration;
        }

        return montantTotal;
    }
    
    
    private boolean estDansMoisCourant(Date date) {
        Calendar calDateGarde = Calendar.getInstance();
        calDateGarde.setTime(date); // Convertir la Date en Calendar

        Calendar calDateCourante = Calendar.getInstance(); // Date actuelle

        // Comparer l'année et le mois
        return calDateGarde.get(Calendar.YEAR) == calDateCourante.get(Calendar.YEAR) &&
               calDateGarde.get(Calendar.MONTH) == calDateCourante.get(Calendar.MONTH);
    }
}

