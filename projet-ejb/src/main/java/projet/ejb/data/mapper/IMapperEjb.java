package projet.ejb.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoParent;
import projet.commun.dto.DtoTarif;
import projet.ejb.data.Compte;
import projet.ejb.data.Parent;
import projet.ejb.data.Tarif;

 
@Mapper( componentModel = "cdi" )
public interface IMapperEjb {  
	
	static final IMapperEjb INSTANCE = Mappers.getMapper( IMapperEjb.class );
	
	
	// Compte
	
	Compte map( DtoCompte source );
	
	DtoCompte map( Compte source );
	
	// Parent
	
	Parent map( DtoParent source );
	
	DtoParent map( Parent source );
	
	// Tarif
	
	Tarif map( DtoTarif source );
	
	DtoTarif map( Tarif source );
	
}
