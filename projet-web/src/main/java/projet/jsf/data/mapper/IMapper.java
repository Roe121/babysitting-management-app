package projet.jsf.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoParent;
import projet.commun.dto.DtoTarif;
import projet.jsf.data.Compte;
import projet.jsf.data.Parent;
import projet.jsf.data.Tarif;

@Mapper(componentModel = "cdi")
public interface IMapper {

	// -------
	// Compte
	// -------

	Compte map(DtoCompte source);

	DtoCompte map(Compte source);

	Compte duplicate(Compte source);

	Compte update(@MappingTarget Compte target, Compte source);

	// -------
	// Parent
	// -------

	Parent map(DtoParent source);

	DtoParent map(Parent source);

	Parent duplicate(Parent source);

	Parent update(@MappingTarget Parent target, Parent source);

	// -------
	// Tarif
	// -------

	Tarif map(DtoTarif source);

	DtoTarif map(Tarif source);

	Tarif duplicate(Tarif source);

	Tarif update(@MappingTarget Tarif target, Tarif source);

}
