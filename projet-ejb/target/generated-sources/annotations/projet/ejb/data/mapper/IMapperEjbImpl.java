package projet.ejb.data.mapper;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoContrat;
import projet.commun.dto.DtoParent;
import projet.ejb.data.Compte;
import projet.ejb.data.Contrat;
import projet.ejb.data.Parent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T14:16:09+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240820-0604, environment: Java 22.0.2 (Eclipse Adoptium)"
)
@ApplicationScoped
public class IMapperEjbImpl implements IMapperEjb {

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
        dtoParent.setId( source.getId() );
        dtoParent.setNom( source.getNom() );
        dtoParent.setPrenom( source.getPrenom() );
        dtoParent.setTelephone( source.getTelephone() );

        return dtoParent;
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
        if ( source.getTarifHoraire() != null ) {
            contrat.setTarifHoraire( source.getTarifHoraire() );
        }
        if ( source.getIndemniteEntretienTauxHoraire() != null ) {
            contrat.setIndemniteEntretienTauxHoraire( source.getIndemniteEntretienTauxHoraire() );
        }
        if ( source.getIndemniteEntretienMinimum() != null ) {
            contrat.setIndemniteEntretienMinimum( source.getIndemniteEntretienMinimum() );
        }
        if ( source.getIndemniteRepas() != null ) {
            contrat.setIndemniteRepas( source.getIndemniteRepas() );
        }

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
        dtoContrat.setId( source.getId() );
        dtoContrat.setIndemniteEntretienMinimum( source.getIndemniteEntretienMinimum() );
        dtoContrat.setIndemniteEntretienTauxHoraire( source.getIndemniteEntretienTauxHoraire() );
        dtoContrat.setIndemniteRepas( source.getIndemniteRepas() );
        dtoContrat.setNomEnfant( source.getNomEnfant() );
        dtoContrat.setParent( map( source.getParent() ) );
        dtoContrat.setPrenomEnfant( source.getPrenomEnfant() );
        dtoContrat.setTarifHoraire( source.getTarifHoraire() );

        return dtoContrat;
    }
}
