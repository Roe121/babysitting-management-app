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

import projet.ejb.dao.IDaoContrat;
import projet.ejb.data.Contrat;

@Stateless
@Local
@TransactionAttribute(MANDATORY)
public class DaoContrat implements IDaoContrat {

	private static final Logger logger = LoggerFactory.getLogger(DaoContrat.class);

	// Champs

	@PersistenceContext
	private EntityManager em;

	// Actions

	@Override
	public int inserer(Contrat contrat) {
		em.persist(contrat);
		em.flush();
		return contrat.getId();
	}

	@Override
	public void modifier(Contrat contrat) {
		em.merge(contrat);
	}

	@Override
	public void supprimer(int idContrat) {
		em.remove(retrouver(idContrat));
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public Contrat retrouver(int idContrat) {
		return em.find(Contrat.class, idContrat);
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<Contrat> listerTout() {
		em.clear();
		var jpql = "SELECT c FROM Contrat c ORDER BY c.nomEnfant";
		var query = em.createQuery(jpql, Contrat.class);
		return query.getResultList();
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<Contrat> listerParParent(int idParent) {
		
		return em.createQuery("SELECT c FROM Contrat c WHERE c.parent.id = :idParent", Contrat.class)
				.setParameter("idParent", idParent).getResultList();
	}


}
