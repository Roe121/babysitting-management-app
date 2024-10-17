package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoContrat;
import projet.commun.dto.DtoParent;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceContrat;
import projet.commun.service.IServiceParent; // Nouveau service pour gérer les parents
import projet.jsf.data.Contrat;
import projet.jsf.data.Parent; // Ajout de la classe Parent
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelContrat implements Serializable {

	
	private static final Logger logger = LoggerFactory.getLogger(ModelContrat.class);
	
    //-------
    // Champs
    //-------
    
	
    private List<Contrat> liste;
    private Contrat courant;
    private int idParentCourant;


	@EJB
    private IServiceContrat serviceContrat;

    @EJB
    private IServiceParent serviceParent; // EJB pour interagir avec les parents

    @Inject
    private IMapper mapper;

    //-------
    // Getters 
    //-------

    public List<Contrat> getListe() {
        if (liste == null) {
            liste = new ArrayList<>();
            for (DtoContrat dto : serviceContrat.listerTout()) {
                liste.add(mapper.map(dto));
            }
        }
        return liste;
    }

    public Contrat getCourant() {
        if (courant == null) {
            courant = new Contrat();
        }
        return courant;
    }
    
    public int getIdParentCourant() {
		return idParentCourant;
	}

    public void setIdParentCourant(int idParentCourant) {
		this.idParentCourant = idParentCourant;
	}
    //-------
    // Initialisations
    //-------



	public String actualiserCourant() {
		if ( courant != null ) {
			DtoContrat dto = serviceContrat.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "Le contrat demandé n'existe pas" );
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
				DtoParent parentCourant = serviceParent.retrouver( idParentCourant );
				courant.setParent(mapper.map(parentCourant));
				serviceContrat.inserer( mapper.map(courant) );
			} else {
				serviceContrat.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "/pages/admin/contrat/liste.xhtml?id=" + idParentCourant + "&faces-redirect=true";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}


    public String supprimer(Contrat item) {
        try {
            serviceContrat.supprimer(item.getId());
            liste.remove(item);
            UtilJsf.messageInfo("Suppression effectuée avec succès.");
        } catch (ExceptionValidation e) {
            UtilJsf.messageError(e);
        }
        return null;
    }
}
