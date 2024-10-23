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

import projet.commun.dto.DtoTarif;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceTarif;
import projet.ejb.dao.IDaoTarif;
import projet.ejb.data.Tarif;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceTarif implements IServiceTarif {

	// Champs
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceTarif.class);

	@PersistenceContext
	private EntityManager	em;
	
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoTarif daoTarif;


	// Actions

	@Override
	public int inserer(DtoTarif dtoTarif) throws ExceptionValidation {
		verifierValiditeDonnees(dtoTarif);
		int id = daoTarif.inserer(mapper.map(dtoTarif));

		return id;
	}

	@Override
	public void modifier(DtoTarif dtoTarif) throws ExceptionValidation {
		verifierValiditeDonnees(dtoTarif);
		daoTarif.modifier(mapper.map(dtoTarif));
	}

	@Override
	public void supprimer(int idTarif) throws ExceptionValidation {
		daoTarif.supprimer(idTarif);
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public DtoTarif retrouver(int idTarif) {
		return mapper.map(daoTarif.retrouver(idTarif));
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<DtoTarif> listerTout() {
		List<DtoTarif> liste = new ArrayList<>();
		for (Tarif Tarif : daoTarif.listerTout()) {
			liste.add(mapper.map(Tarif));
		}
		return liste;
	}
	

	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoTarif dtoTarif) throws ExceptionValidation {

		StringBuilder message = new StringBuilder();

		/*
		 * if (dtoTarif.getPseudo() == null || dtoTarif.getPseudo().isEmpty()) {
		 * message.append("\nLe pseudo est absent."); } else if
		 * (dtoTarif.getPseudo().length() < 3) {
		 * message.append("\nLe pseudo est trop court."); } else if
		 * (dtoTarif.getPseudo().length() > 25) {
		 * message.append("\nLe pseudo est trop long."); } else if
		 * (!daoTarif.verifierUnicitePseudo(dtoTarif.getPseudo(), dtoTarif.getId())) {
		 * message.append("\nLe pseudo " + dtoTarif.getPseudo() + " est déjà utilisé.");
		 * }
		 * 
		 * if (dtoTarif.getMotDePasse() == null || dtoTarif.getMotDePasse().isEmpty()) {
		 * message.append("\nLe mot de passe est absent."); } else if
		 * (dtoTarif.getMotDePasse().length() < 3) {
		 * message.append("\nLe mot de passe est trop court."); } else if
		 * (dtoTarif.getMotDePasse().length() > 25) {
		 * message.append("\nLe mot de passe est trop long."); }
		 * 
		 * if (message.length() > 0) { throw new
		 * ExceptionValidation(message.toString().substring(1)); }
		 */
	
    	
	}

}
