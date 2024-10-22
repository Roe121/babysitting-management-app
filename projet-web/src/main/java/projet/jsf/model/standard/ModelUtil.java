package projet.jsf.model.standard;


import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projet.commun.dto.DtoGarde;
import projet.commun.service.IServiceGarde;
import projet.commun.service.IServiceParent;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@Named
@ViewScoped
public class ModelUtil implements Serializable{
    
    private static final Logger logger = LoggerFactory.getLogger(ModelParent.class);

	private static final long serialVersionUID = 1L;
	private Date moisSelectionne;
    private List<DtoGarde> gardes;
    private double totalMontant;
    
    // Injecter le service nécessaire
	@EJB
    private IServiceGarde serviceGarde;
	@EJB
    private IServiceParent serviceParent;
	
	@Inject
	private ModelConnexion modelConnexion;

	public List<Date> getMoisDisponibles() {
	    List<Date> mois = new ArrayList<>();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	    try {
	        // Ajouter manuellement chaque date correspondant au premier jour de chaque mois en 2024
	        mois.add(formatter.parse("2024-01-01"));
	        mois.add(formatter.parse("2024-02-01"));
	        mois.add(formatter.parse("2024-03-01"));
	        mois.add(formatter.parse("2024-04-01"));
	        mois.add(formatter.parse("2024-05-01"));
	        mois.add(formatter.parse("2024-06-01"));
	        mois.add(formatter.parse("2024-07-01"));
	        mois.add(formatter.parse("2024-08-01"));
	        mois.add(formatter.parse("2024-09-01"));
	        mois.add(formatter.parse("2024-10-01"));
	        mois.add(formatter.parse("2024-11-01"));
	        mois.add(formatter.parse("2024-12-01"));
	    } catch (ParseException e) {
	        // Gérer l'erreur si le format de la date est incorrect
	        e.printStackTrace();
	    }

	    logger.info("liste des mois disponibles : {}", mois);
	    return mois;
	}

    public void consulterGarde() {
        // Récupérer l'ID du parent connecté
    	logger.info("mois sélectionné dans consulter garde : {}", moisSelectionne);
    	int idParent = serviceParent.retrouverParCompteId(modelConnexion.getCompteActif().getId()).getId();
    	logger.info("le parent de ce compte est : {}", serviceParent.retrouverParCompteId(modelConnexion.getCompteActif().getId()).getPrenom());

        gardes = serviceGarde.listerParMoisEtParent(moisSelectionne, idParent);
        totalMontant = calculerTotal(gardes);
    }

    private double calculerTotal(List<DtoGarde> gardes) {
        double total = 0.0;
        for (DtoGarde garde : gardes) {
            total += calculerMontant(garde); // Supposons que DtoGarde a une méthode pour le montant à payer
        }
        return total;
    }

    public Date getMoisSelectionne() {
        logger.info("mois sélectionné : {}", moisSelectionne);
        return moisSelectionne;
    }

    public void setMoisSelectionne(Date moisSelectionne) {
        this.moisSelectionne = moisSelectionne;
    }

    public List<DtoGarde> getGardes() {
        return gardes;
    }

    public double getTotalMontant() {
        return totalMontant;
    }
    
    public double calculerMontant(DtoGarde garde) {
        return serviceGarde.getMontantAPayer(garde);
    }
}

