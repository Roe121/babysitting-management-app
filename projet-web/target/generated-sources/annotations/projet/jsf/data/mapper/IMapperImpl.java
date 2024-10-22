package projet.jsf.data.mapper;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoContrat;
import projet.commun.dto.DtoGarde;
import projet.commun.dto.DtoParent;
import projet.jsf.data.Compte;
import projet.jsf.data.Contrat;
import projet.jsf.data.Garde;
import projet.jsf.data.Parent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-20T17:03:09+0200",
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

    @Override
    public Contrat map(DtoContrat source) {
        if ( source == null ) {
            return null;
        }

        Contrat contrat = new Contrat();

        contrat.setId( source.getId() );
        contrat.setParent( map( source.getParent() ) );
        contrat.setNomEnfant( source.getNomEnfant() );
        contrat.setPrenomEnfant( source.getPrenomEnfant() );
        contrat.setDateNaissanceEnfant( source.getDateNaissanceEnfant() );
        contrat.setDateDebut( source.getDateDebut() );
        contrat.setDateFin( source.getDateFin() );
        contrat.setTarifHoraire( source.getTarifHoraire() );
        contrat.setIndemniteEntretienTauxHoraire( source.getIndemniteEntretienTauxHoraire() );
        contrat.setIndemniteEntretienMinimum( source.getIndemniteEntretienMinimum() );
        contrat.setIndemniteRepas( source.getIndemniteRepas() );

        return contrat;
    }

    @Override
    public DtoContrat map(Contrat source) {
        if ( source == null ) {
            return null;
        }

        DtoContrat dtoContrat = new DtoContrat();

        dtoContrat.setDateDebut( source.getDateDebut() );
        dtoContrat.setDateFin( source.getDateFin() );
        dtoContrat.setDateNaissanceEnfant( source.getDateNaissanceEnfant() );
        if ( source.getId() != null ) {
            dtoContrat.setId( source.getId() );
        }
        dtoContrat.setIndemniteEntretienMinimum( source.getIndemniteEntretienMinimum() );
        dtoContrat.setIndemniteEntretienTauxHoraire( source.getIndemniteEntretienTauxHoraire() );
        dtoContrat.setIndemniteRepas( source.getIndemniteRepas() );
        dtoContrat.setNomEnfant( source.getNomEnfant() );
        dtoContrat.setParent( map( source.getParent() ) );
        dtoContrat.setPrenomEnfant( source.getPrenomEnfant() );
        dtoContrat.setTarifHoraire( source.getTarifHoraire() );

        return dtoContrat;
    }

    @Override
    public Contrat duplicate(Contrat source) {
        if ( source == null ) {
            return null;
        }

        Contrat contrat = new Contrat();

        contrat.setId( source.getId() );
        contrat.setParent( duplicate( source.getParent() ) );
        contrat.setNomEnfant( source.getNomEnfant() );
        contrat.setPrenomEnfant( source.getPrenomEnfant() );
        contrat.setDateNaissanceEnfant( source.getDateNaissanceEnfant() );
        contrat.setDateDebut( source.getDateDebut() );
        contrat.setDateFin( source.getDateFin() );
        contrat.setTarifHoraire( source.getTarifHoraire() );
        contrat.setIndemniteEntretienTauxHoraire( source.getIndemniteEntretienTauxHoraire() );
        contrat.setIndemniteEntretienMinimum( source.getIndemniteEntretienMinimum() );
        contrat.setIndemniteRepas( source.getIndemniteRepas() );

        return contrat;
    }

    @Override
    public Contrat update(Contrat target, Contrat source) {
        if ( source == null ) {
            return target;
        }

        target.setId( source.getId() );
        if ( source.getParent() != null ) {
            if ( target.getParent() == null ) {
                target.setParent( new Parent() );
            }
            update( target.getParent(), source.getParent() );
        }
        else {
            target.setParent( null );
        }
        target.setNomEnfant( source.getNomEnfant() );
        target.setPrenomEnfant( source.getPrenomEnfant() );
        target.setDateNaissanceEnfant( source.getDateNaissanceEnfant() );
        target.setDateDebut( source.getDateDebut() );
        target.setDateFin( source.getDateFin() );
        target.setTarifHoraire( source.getTarifHoraire() );
        target.setIndemniteEntretienTauxHoraire( source.getIndemniteEntretienTauxHoraire() );
        target.setIndemniteEntretienMinimum( source.getIndemniteEntretienMinimum() );
        target.setIndemniteRepas( source.getIndemniteRepas() );

        return target;
    }

    @Override
    public Garde map(DtoGarde source) {
        if ( source == null ) {
            return null;
        }

        Garde garde = new Garde();

        garde.setId( source.getId() );
        garde.setContrat( map( source.getContrat() ) );
        garde.setDateJour( source.getDateJour() );
        garde.setHeureArrivee( source.getHeureArrivee() );
        garde.setHeureDepart( source.getHeureDepart() );
        garde.setPrisRepas( source.isPrisRepas() );

        return garde;
    }

    @Override
    public DtoGarde map(Garde source) {
        if ( source == null ) {
            return null;
        }

        DtoGarde dtoGarde = new DtoGarde();

        dtoGarde.setContrat( map( source.getContrat() ) );
        dtoGarde.setDateJour( source.getDateJour() );
        dtoGarde.setHeureArrivee( source.getHeureArrivee() );
        dtoGarde.setHeureDepart( source.getHeureDepart() );
        if ( source.getId() != null ) {
            dtoGarde.setId( source.getId() );
        }
        dtoGarde.setPrisRepas( source.isPrisRepas() );

        return dtoGarde;
    }

    @Override
    public Garde duplicate(Garde source) {
        if ( source == null ) {
            return null;
        }

        Garde garde = new Garde();

        garde.setId( source.getId() );
        garde.setContrat( duplicate( source.getContrat() ) );
        garde.setDateJour( source.getDateJour() );
        garde.setHeureArrivee( source.getHeureArrivee() );
        garde.setHeureDepart( source.getHeureDepart() );
        garde.setPrisRepas( source.isPrisRepas() );

        return garde;
    }

    @Override
    public Garde update(Garde target, Garde source) {
        if ( source == null ) {
            return target;
        }

        target.setId( source.getId() );
        if ( source.getContrat() != null ) {
            if ( target.getContrat() == null ) {
                target.setContrat( new Contrat() );
            }
            update( target.getContrat(), source.getContrat() );
        }
        else {
            target.setContrat( null );
        }
        target.setDateJour( source.getDateJour() );
        target.setHeureArrivee( source.getHeureArrivee() );
        target.setHeureDepart( source.getHeureDepart() );
        target.setPrisRepas( source.isPrisRepas() );

        return target;
    }
}
