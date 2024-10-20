package projet.ejb.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoContrat;
import projet.commun.dto.DtoGarde;
import projet.commun.dto.DtoParent;
import projet.ejb.data.Compte;
import projet.ejb.data.Contrat;
import projet.ejb.data.Garde;
import projet.ejb.data.Parent;

 
@Mapper( componentModel = "cdi" )
public interface IMapperEjb {  
	
	static final IMapperEjb INSTANCE = Mappers.getMapper( IMapperEjb.class );
	
	
	// Compte
	
	Compte map( DtoCompte source );
	
	DtoCompte map( Compte source );
	
	// Parent
	
	Parent map( DtoParent source );
	
	DtoParent map( Parent source );
	
	// Contrat
	
	Contrat map( DtoContrat source );
	
	DtoContrat map( Contrat source );
	
	// Garde
	
	Garde map( DtoGarde source );
	
	DtoGarde map( Garde source );
	
	
}
