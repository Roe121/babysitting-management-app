package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoTarif;
import projet.ejb.data.Tarif;


@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoTarif implements IDaoTarif {

	
	// Champs
	
	@PersistenceContext
	private EntityManager	em;
	
	
	// Actions
	
	@Override
	public int inserer(Tarif tarif) {
		em.persist(tarif);
		em.flush();
		return tarif.getId();
	}

	@Override
	public void modifier(Tarif tarif) {
		em.merge( tarif );
	}

	@Override
	public void supprimer(int idtarif) {
		em.remove( retrouver(idtarif) );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Tarif retrouver(int idtarif) {
		return em.find( Tarif.class, idtarif );
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Tarif> listerTout() {
		em.clear();
		var jpql = "SELECT t FROM Tarif t ORDER BY t.typeTarif";
		var query = em.createQuery( jpql, Tarif.class );
		return query.getResultList();
	}


	
}
