package projet.jsf.data.mapper;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoParent;
import projet.jsf.data.Compte;
import projet.jsf.data.Parent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-13T12:00:08+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@ApplicationScoped
public class IMapperImpl implements IMapper {

    @Override
    public Compte map(DtoCompte source) {
        if ( source == null ) {
            return null;
        }

        Compte compte = new Compte();

        compte.setId( source.getId() );
        compte.setPseudo( source.getPseudo() );
        compte.setMotDePasse( source.getMotDePasse() );
        compte.setFlagAdmin( source.isFlagAdmin() );

        return compte;
    }

    @Override
    public DtoCompte map(Compte source) {
        if ( source == null ) {
            return null;
        }

        DtoCompte dtoCompte = new DtoCompte();

        dtoCompte.setFlagAdmin( source.isFlagAdmin() );
        if ( source.getId() != null ) {
            dtoCompte.setId( source.getId() );
        }
        dtoCompte.setMotDePasse( source.getMotDePasse() );
        dtoCompte.setPseudo( source.getPseudo() );

        return dtoCompte;
    }

    @Override
    public Compte duplicate(Compte source) {
        if ( source == null ) {
            return null;
        }

        Compte compte = new Compte();

        compte.setId( source.getId() );
        compte.setPseudo( source.getPseudo() );
        compte.setMotDePasse( source.getMotDePasse() );
        compte.setFlagAdmin( source.isFlagAdmin() );

        return compte;
    }

    @Override
    public Compte update(Compte target, Compte source) {
        if ( source == null ) {
            return target;
        }

        target.setId( source.getId() );
        target.setPseudo( source.getPseudo() );
        target.setMotDePasse( source.getMotDePasse() );
        target.setFlagAdmin( source.isFlagAdmin() );

        return target;
    }

    @Override
    public Parent map(DtoParent source) {
        if ( source == null ) {
            return null;
        }

        Parent parent = new Parent();

        parent.setId( source.getId() );
        parent.setCompte( map( source.getCompte() ) );
        parent.setNom( source.getNom() );
        parent.setPrenom( source.getPrenom() );
        parent.setAdressePostale( source.getAdressePostale() );
        parent.setAdresseEmail( source.getAdresseEmail() );
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
        if ( source.getId() != null ) {
            dtoParent.setId( source.getId() );
        }
        dtoParent.setNom( source.getNom() );
        dtoParent.setPrenom( source.getPrenom() );
        dtoParent.setTelephone( source.getTelephone() );

        return dtoParent;
    }

    @Override
    public Parent duplicate(Parent source) {
        if ( source == null ) {
            return null;
        }

        Parent parent = new Parent();

        parent.setId( source.getId() );
        parent.setCompte( duplicate( source.getCompte() ) );
        parent.setNom( source.getNom() );
        parent.setPrenom( source.getPrenom() );
        parent.setAdressePostale( source.getAdressePostale() );
        parent.setAdresseEmail( source.getAdresseEmail() );
        parent.setTelephone( source.getTelephone() );

        return parent;
    }

    @Override
    public Parent update(Parent target, Parent source) {
        if ( source == null ) {
            return target;
        }

        target.setId( source.getId() );
        if ( source.getCompte() != null ) {
            if ( target.getCompte() == null ) {
                target.setCompte( new Compte() );
            }
            update( target.getCompte(), source.getCompte() );
        }
        else {
            target.setCompte( null );
        }
        target.setNom( source.getNom() );
        target.setPrenom( source.getPrenom() );
        target.setAdressePostale( source.getAdressePostale() );
        target.setAdresseEmail( source.getAdresseEmail() );
        target.setTelephone( source.getTelephone() );

        return target;
    }
}
