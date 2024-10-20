package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projet.commun.dto.DtoContrat;
import projet.commun.dto.DtoGarde;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceContrat;
import projet.commun.service.IServiceGarde;
import projet.jsf.data.Garde;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelGarde implements Serializable {

	
	private static final Logger logger = LoggerFactory.getLogger(ModelGarde.class);
	
    //-------
    // Champs
    //-------
    
	
    private List<Garde> liste;
    private Garde courant;
    private int idContratCourant;


	@EJB
    private IServiceGarde serviceGarde;
	
	@EJB
    private IServiceContrat serviceContrat;

    @Inject
    private IMapper mapper;

    //-------
    // Getters 
    //-------

    public List<Garde> getListe() {
        if (liste == null) {
            liste = new ArrayList<>();
            for (DtoGarde dto : serviceGarde.listerTout()) {
                liste.add(mapper.map(dto));
            }
        }
        return liste;
    }

    public Garde getCourant() {
        if (courant == null) {
            courant = new Garde();
        }
        logger.info("la garde courante est : {}", courant.toString());
        return courant;
    }
    
    public int getIdContratCourant() {
		return idContratCourant;
	}

    public void setIdContratCourant(int idContratCourant) {
		this.idContratCourant = idContratCourant;
	}
    
    public Date getDateJour() {
        if (courant.getDateJour() == null) {
            courant.setDateJour(new Date()); // Assigner la date du jour si elle est nulle
        }
        return courant.getDateJour();
    }
    

    //-------
    // Initialisations
    //-------



	public String actualiserCourant() {
		if ( courant != null ) {
			DtoGarde dto = serviceGarde.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "Le garde demandé n'existe pas" );
				return "test/liste";
			} else {
				courant = mapper.map( dto );
			}
		}
		return null;
	}

    //-------
    // Actions
    //-------

    public String validerMiseAJour() {
		try {
			
			if ( courant.getId() == null) {
				DtoContrat contratCourant = serviceContrat.retrouver( idContratCourant );
				courant.setContrat(mapper.map(contratCourant));
				serviceGarde.inserer( mapper.map(courant) );
			} else {
				serviceGarde.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "/pages/admin/garde/listeParContrat.xhtml?id=" + idContratCourant + "&faces-redirect=true";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}


    public String supprimer(Garde item) {
        try {
            serviceGarde.supprimer(item.getId());
            liste.remove(item);
            UtilJsf.messageInfo("Suppression effectuée avec succès.");
        } catch (ExceptionValidation e) {
            UtilJsf.messageError(e);
        }
        return null;
    }
}
