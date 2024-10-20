package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projet.ejb.dao.IDaoGarde;
import projet.ejb.data.Garde;

@Stateless
@Local
@TransactionAttribute(MANDATORY)
public class DaoGarde implements IDaoGarde {

	private static final Logger logger = LoggerFactory.getLogger(DaoGarde.class);

	// Champs

	@PersistenceContext
	private EntityManager em;

	// Actions

	@Override
	public int inserer(Garde garde) {
		em.persist(garde);
		em.flush();
		return garde.getId();
	}

	@Override
	public void modifier(Garde garde) {
		em.merge(garde);
	}

	@Override
	public void supprimer(int idGarde) {
		em.remove(retrouver(idGarde));
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public Garde retrouver(int idGarde) {
		logger.info("garde courante est {}",em.find(Garde.class, idGarde).isPrisRepas());
		return em.find(Garde.class, idGarde);
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<Garde> listerTout() {
		em.clear();
		var jpql = "SELECT g FROM Garde g";
		var query = em.createQuery(jpql, Garde.class);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<Garde> listerParContrat(int idContrat) {
		
		logger.info("DAO CONTRAT LA LISTE PAR ID CONTRAT EST {}", em.createQuery("SELECT g FROM Garde g WHERE g.contrat.id = :idContrat", Garde.class)
				.setParameter("idContrat", idContrat).getResultList());
		return em.createQuery("SELECT g FROM Garde g WHERE g.contrat.id = :idContrat", Garde.class)
				.setParameter("idContrat", idContrat).getResultList();
	}



}
