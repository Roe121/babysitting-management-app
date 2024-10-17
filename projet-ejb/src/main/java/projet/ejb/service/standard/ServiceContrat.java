package projet.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projet.commun.dto.DtoContrat;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceContrat;
import projet.ejb.dao.IDaoContrat;
import projet.ejb.data.Contrat;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceContrat implements IServiceContrat {

	// Champs
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceContrat.class);

	@PersistenceContext
	private EntityManager	em;
	
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoContrat daoContrat;


	// Actions

	@Override
	public int inserer(DtoContrat dtoContrat) throws ExceptionValidation {
		verifierValiditeDonnees(dtoContrat);
		int id = daoContrat.inserer(mapper.map(dtoContrat));

		return id;
	}

	@Override
	public void modifier(DtoContrat dtoContrat) throws ExceptionValidation {
		verifierValiditeDonnees(dtoContrat);
		daoContrat.modifier(mapper.map(dtoContrat));
	}

	@Override
	public void supprimer(int idContrat) throws ExceptionValidation {
		daoContrat.supprimer(idContrat);
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoContrat retrouver(int idContrat) {
		return mapper.map(daoContrat.retrouver(idContrat));
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoContrat> listerTout() {
		List<DtoContrat> liste = new ArrayList<>();
		for (Contrat contrat : daoContrat.listerTout()) {
			liste.add(mapper.map(contrat));
		}
		return liste;
	}
	
	@Override
	public List<DtoContrat> listerParParent(Integer idParent) {
	    List<Contrat> contrats = daoContrat.listerParParent(idParent);
	    List<DtoContrat> listeDto = new ArrayList<>();
	    for (Contrat contrat : contrats) {
	        listeDto.add(mapper.map(contrat));  
	    }
	    return listeDto;
	}

	
	

	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoContrat dtoContrat) throws ExceptionValidation {

	    StringBuilder message = new StringBuilder();

	    // Vérification du nom de l'enfant
	    if (dtoContrat.getNomEnfant() == null || dtoContrat.getNomEnfant().isEmpty()) {
	        message.append("\nLe nom de l'enfant est absent.");
	    } else if (dtoContrat.getNomEnfant().length() < 2) {
	        message.append("\nLe nom de l'enfant est trop court.");
	    } else if (dtoContrat.getNomEnfant().length() > 50) {
	        message.append("\nLe nom de l'enfant est trop long.");
	    }

	    // Vérification du prénom de l'enfant
	    if (dtoContrat.getPrenomEnfant() == null || dtoContrat.getPrenomEnfant().isEmpty()) {
	        message.append("\nLe prénom de l'enfant est absent.");
	    } else if (dtoContrat.getPrenomEnfant().length() < 2) {
	        message.append("\nLe prénom de l'enfant est trop court.");
	    } else if (dtoContrat.getPrenomEnfant().length() > 50) {
	        message.append("\nLe prénom de l'enfant est trop long.");
	    }

	    // Vérification des dates de début et de fin
	    if (dtoContrat.getDateDebut() == null) {
	        message.append("\nLa date de début du contrat est absente.");
	    }
	    if (dtoContrat.getDateFin() == null) {
	        message.append("\nLa date de fin du contrat est absente.");
	    } else if (dtoContrat.getDateDebut() != null && dtoContrat.getDateFin().before(dtoContrat.getDateDebut())) {
	        message.append("\nLa date de fin du contrat doit être postérieure à la date de début.");
	    }

	    // Vérification du tarif horaire
	    if (dtoContrat.getTarifHoraire() <= 0) {
	        message.append("\nLe tarif horaire doit être supérieur à zéro.");
	    }

	    // Vérification de l'indemnité d'entretien
	    if (dtoContrat.getIndemniteEntretienTauxHoraire() < 0) {
	        message.append("\nL'indemnité d'entretien horaire ne peut pas être négative.");
	    }

	    if (dtoContrat.getIndemniteEntretienMinimum() < 0) {
	        message.append("\nL'indemnité d'entretien minimum ne peut pas être négative.");
	    }

	    // Vérification de l'indemnité repas (si spécifiée)
	    if (dtoContrat.getIndemniteRepas() != null && dtoContrat.getIndemniteRepas() < 0) {
	        message.append("\nL'indemnité de repas ne peut pas être négative.");
	    }

	    // Si un message d'erreur est accumulé, on lance une exception
	    if (message.length() > 0) {
	        throw new ExceptionValidation(message.toString().substring(1));  // Supprime le premier caractère '\n'
	    }
	}





}
