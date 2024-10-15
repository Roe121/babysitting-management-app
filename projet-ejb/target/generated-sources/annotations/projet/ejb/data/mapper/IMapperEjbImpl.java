package projet.ejb.data.mapper;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoParent;
import projet.ejb.data.Compte;
import projet.ejb.data.Parent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-14T16:56:47+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@ApplicationScoped
public class IMapperEjbImpl implements IMapperEjb {

    @Override
    public Compte map(DtoCompte source) {
        if ( source == null ) {
            return null;
        }

        Compte compte = new Compte();

        compte.setFlagAdmin( source.isFlagAdmin() );
        compte.setId( source.getId() );
        compte.setMotDePasse( source.getMotDePasse() );
        compte.setPseudo( source.getPseudo() );

        return compte;
    }

    @Override
    public DtoCompte map(Compte source) {
        if ( source == null ) {
            return null;
        }

        DtoCompte dtoCompte = new DtoCompte();

        dtoCompte.setFlagAdmin( source.isFlagAdmin() );
        dtoCompte.setId( source.getId() );
        dtoCompte.setMotDePasse( source.getMotDePasse() );
        dtoCompte.setPseudo( source.getPseudo() );

        return dtoCompte;
    }

    @Override
    public Parent map(DtoParent source) {
        if ( source == null ) {
            return null;
        }

        Parent parent = new Parent();

        parent.setAdresseEmail( source.getAdresseEmail() );
        parent.setAdressePostale( source.getAdressePostale() );
        parent.setCompte( map( source.getCompte() ) );
        parent.setId( source.getId() );
        parent.setNom( source.getNom() );
        parent.setPrenom( source.getPrenom() );
        parent.setTelephone( source.getTelephone() );

        return parent;
    }

    @Override
    public DtoParent map(Parent source) {
        if ( source == null ) {
            return null;
        }

        DtoParent dtoParent = new DtoParent();

        dtoParent.setAdresseEmail( source.getAdresseEmail() );
        dtoParent.setAdressePostale( source.getAdressePostale() );
        dtoParent.setCompte( map( source.getCompte() ) );
        dtoParent.setId( source.getId() );
        dtoParent.setNom( source.getNom() );
        dtoParent.setPrenom( source.getPrenom() );
        dtoParent.setTelephone( source.getTelephone() );

        return dtoParent;
    }
}
