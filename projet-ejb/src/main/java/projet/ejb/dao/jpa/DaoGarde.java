package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		
		return em.createQuery("SELECT g FROM Garde g WHERE g.contrat.id = :idContrat", Garde.class)
				.setParameter("idContrat", idContrat).getResultList();
	}
	
	
	
	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public List<Garde> listerParDate(Date dateGarde) {
		

//		logger.info("DAO GARDE LA LISTE PAR DATE EST  {}", em.createQuery("SELECT g FROM Garde g WHERE g.dateJour = :dateGarde", Garde.class)
//				.setParameter("dateGarde", dateGarde).getResultList());
		
		return em.createQuery("SELECT g FROM Garde g WHERE g.dateJour = :dateGarde", Garde.class)
				.setParameter("dateGarde", dateGarde).getResultList();
	}
	
	@Override
	public List<Garde> listerParMoisEtParent(Date dateMois, int idParent) {
        // Utilisation de Calendar pour obtenir le début et la fin du mois donné
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateMois);
        
        // Début du mois
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date premierJourDuMois = cal.getTime();

        // Fin du mois
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date dernierJourDuMois = cal.getTime();
        
        logger.info("DAO GARDE date donnée est {} date debut est  {} fin est {} ", dateMois,premierJourDuMois,dernierJourDuMois);

        // Requête HQL pour récupérer les gardes du parent pour ce mois via le contrat
        String jpql = "SELECT g FROM Garde g JOIN g.contrat c WHERE c.parent.id = :idParent "
                    + "AND g.dateJour BETWEEN :debutMois AND :finMois";

        TypedQuery<Garde> query = em.createQuery(jpql, Garde.class);
        query.setParameter("idParent", idParent);
        query.setParameter("debutMois", premierJourDuMois);
        query.setParameter("finMois", dernierJourDuMois);

        logger.info("DAO GARDE date mois est  {}   idparent est {}", dateMois,idParent);
        logger.info("DAO GARDE listerParMoisEtParent EST  {}", query.getResultList());
//				.setParameter("dateGarde", dateGarde).getResultList());
        return query.getResultList();
    }


}
