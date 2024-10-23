package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Tarif;


public interface IDaoTarif {

	int			inserer( Tarif Tarif );

	void 		modifier( Tarif Tarif );

	void 		supprimer( int idTarif );

	Tarif 		retrouver( int idTarif );

	List<Tarif> listerTout();

}
