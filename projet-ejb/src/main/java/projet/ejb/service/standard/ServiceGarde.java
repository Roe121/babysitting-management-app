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
import projet.commun.dto.DtoGarde;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceGarde;
import projet.ejb.dao.IDaoGarde;
import projet.ejb.data.Contrat;
import projet.ejb.data.Garde;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceGarde implements IServiceGarde {

	// Champs
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceGarde.class);

	@PersistenceContext
	private EntityManager	em;
	
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoGarde daoGarde;


	// Actions

	@Override
	public int inserer(DtoGarde dtoGarde) throws ExceptionValidation {
		verifierValiditeDonnees(dtoGarde);
		int id = daoGarde.inserer(mapper.map(dtoGarde));

		return id;
	}

	@Override
	public void modifier(DtoGarde dtoGarde) throws ExceptionValidation {
		verifierValiditeDonnees(dtoGarde);
		daoGarde.modifier(mapper.map(dtoGarde));
	}

	@Override
	public void supprimer(int idGarde) throws ExceptionValidation {
		daoGarde.supprimer(idGarde);
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoGarde retrouver(int idGarde) {
		return mapper.map(daoGarde.retrouver(idGarde));
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoGarde> listerTout() {
		List<DtoGarde> liste = new ArrayList<>();
		for (Garde garde : daoGarde.listerTout()) {
			liste.add(mapper.map(garde));
		}
		return liste;
	}
	
	@Override
	public List<DtoGarde> listerParContrat(Integer id) {
	    List<Garde> gardes = daoGarde.listerParContrat(id);
	    List<DtoGarde> listeDto = new ArrayList<>();
	    for (Garde garde : gardes) {
	        listeDto.add(mapper.map(garde));  
	    }
	    return listeDto;
	}
	
	

	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoGarde dtoGarde) throws ExceptionValidation {

	    StringBuilder message = new StringBuilder();

	    // Vérification du jour de garde
	    if (dtoGarde.getDateJour() == null) {
	        message.append("\nLa date du jour de garde est absente.");
	    }

	    // Vérification de l'heure d'arrivée
	    if (dtoGarde.getHeureArrivee() == null) {
	        message.append("\nL'heure d'arrivée est absente.");
	    }

	    // Vérification de l'heure de départ
	    if (dtoGarde.getHeureDepart() == null) {
	        message.append("\nL'heure de départ est absente.");
	    } else if (dtoGarde.getHeureArrivee() != null && dtoGarde.getHeureDepart().isBefore(dtoGarde.getHeureArrivee())) {
	        message.append("\nL'heure de départ doit être postérieure à l'heure d'arrivée.");
	    }

	    // Si un message d'erreur est accumulé, on lance une exception
	    if (message.length() > 0) {
	        throw new ExceptionValidation(message.toString().substring(1));  // Supprime le premier caractère '\n'
	    }
	}


}
