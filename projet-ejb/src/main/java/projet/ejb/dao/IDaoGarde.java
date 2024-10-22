package projet.ejb.dao;

import java.util.Date;
import java.util.List;

import projet.ejb.data.Garde;


public interface IDaoGarde {

	int			inserer( Garde garde );

	void 		modifier( Garde garde );

	void 		supprimer( int idGarde );

	Garde 		retrouver( int idGarde );

	List<Garde> listerTout();
	
	List<Garde> listerParContrat(int idContrat);
	
	List<Garde> listerParDate(Date dateGarde);
	
	List<Garde> listerParMoisEtParent(Date dateMois,int idParent);

}
