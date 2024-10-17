package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Contrat;


public interface IDaoContrat {

	int			inserer( Contrat contrat );

	void 		modifier( Contrat contrat );

	void 		supprimer( int idContrat );

	Contrat 		retrouver( int idContrat );

	List<Contrat> listerTout();

	List<Contrat> listerParParent(int idParent);

}
