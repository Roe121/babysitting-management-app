package projet.commun.service;

import java.util.Date;
import java.util.List;

import projet.commun.dto.DtoGarde;
import projet.commun.exception.ExceptionValidation;


public interface IServiceGarde {
	
	int				inserer( DtoGarde dtoGarde ) throws ExceptionValidation;

	void			modifier( DtoGarde dtoGarde ) throws ExceptionValidation; 

	void			supprimer( int idGarde ) throws ExceptionValidation;

	DtoGarde 		retrouver( int idGarde ) ;

	List<DtoGarde>	listerTout() ;
	
	List<DtoGarde> listerParContrat(Integer id);
	
	List<DtoGarde> listerParDate(Date dateGarde);
	
	List<DtoGarde> listerParMoisEtParent(Date dateMois,int idParent);
	
	double calculerHeuresTravaillees(DtoGarde garde);
	
	double getMontantAPayer(DtoGarde garde);
	
}
