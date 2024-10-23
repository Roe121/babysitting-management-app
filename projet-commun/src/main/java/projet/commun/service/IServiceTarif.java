package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoTarif;
import projet.commun.exception.ExceptionValidation;


public interface IServiceTarif {
	
	int				inserer( DtoTarif dtoTarif ) throws ExceptionValidation;

	void			modifier( DtoTarif dtoTarif ) throws ExceptionValidation; 

	void			supprimer( int idTarif ) throws ExceptionValidation;

	DtoTarif 		retrouver( int idTarif ) ;

	List<DtoTarif>	listerTout() ;
	
}
