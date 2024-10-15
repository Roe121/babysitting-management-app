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
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceCompte;
import projet.commun.service.IServiceParent; // Nouveau service pour gérer les parents
import projet.jsf.data.Compte;
import projet.jsf.data.Parent; // Ajout de la classe Parent
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelCompte implements Serializable {

	
	private static final Logger logger = LoggerFactory.getLogger(ModelCompte.class);
	
    //-------
    // Champs
    //-------
    
	
    private List<Compte> liste;
    private Compte courant;
    private Parent parent;

    @EJB
    private IServiceCompte serviceCompte;

    @EJB
    private IServiceParent serviceParent; // EJB pour interagir avec les parents

    @Inject
    private IMapper mapper;

    //-------
    // Getters 
    //-------

    public List<Compte> getListe() {
        if (liste == null) {
            liste = new ArrayList<>();
            for (DtoCompte dto : serviceCompte.listerTout()) {
                liste.add(mapper.map(dto));
            }
        }
        return liste;
    }

    public Compte getCourant() {
        if (courant == null) {
            courant = new Compte();
        }
        return courant;
    }
    
    public Parent getParent() {
        if (parent == null) {
        	parent = new Parent();
        }
        return parent;
    }


    //-------
    // Initialisations
    //-------

    public String actualiserCourant() {
		if ( courant != null ) {
			DtoCompte dto = serviceCompte.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "Le compte demandé n'existe pas" );
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
            if (courant.getId() == null) {
                // Si le compte est nouveau (pas encore d'ID)
                if (courant.isFlagAdmin()) {
                    // Cas d'un compte admin (assistante maternelle)
                    serviceCompte.inserer(mapper.map(courant));
                    UtilJsf.messageInfo("Ajout d'un compte assistante maternelle avec succès.");
                } else {
                    // Cas d'un compte parent

                    // Insérer le compte d'abord
                    int compteCreeId = serviceCompte.inserer(mapper.map(courant));
                    
                    DtoCompte compteCree = serviceCompte.retrouver(compteCreeId);

                    // Associer le compte au parent
                    parent.setCompte(mapper.map(compteCree));  // Lier le compte au parent

                    // Sauvegarder le parent avec le compte associé
                    serviceParent.inserer(mapper.map(parent));

                    UtilJsf.messageInfo("Ajout d'un compte parent avec succès.");
                }
            } else {
                // Cas de modification d'un compte existant
                serviceCompte.modifier(mapper.map(courant));
                UtilJsf.messageInfo("Mise à jour du compte effectuée avec succès.");
            }

            return "liste";  // Redirige vers la liste après succès
        } catch (ExceptionValidation e) {
            logger.error("Erreur lors de la mise à jour du compte : ", e);
            UtilJsf.messageError(e);
            return null;
        } catch (Exception e) {
            // Capture toute exception inattendue
            logger.error("Erreur inattendue lors de la mise à jour du compte : ", e);
            UtilJsf.messageError("Erreur inattendue lors du traitement.");
            return null;
        }
    }
    
    public String validerMiseAJourAss() {
    	logger.info("flagAdmin is {}.", this.courant.isFlagAdmin());
        try {
            if (courant.getId() == null) {
                // Si le compte est nouveau (pas encore d'ID)
                courant.setFlagAdmin(true);
                    
                serviceCompte.inserer(mapper.map(courant));
                UtilJsf.messageInfo("Ajout d'un compte assistante maternelle avec succès.");
                
            } else {
                // Cas de modification d'un compte existant
                serviceCompte.modifier(mapper.map(courant));
                UtilJsf.messageInfo("Mise à jour du compte effectuée avec succès.");
            }

            return "liste";  // Redirige vers la liste après succès
        } catch (ExceptionValidation e) {
            logger.error("Erreur lors de la mise à jour du compte : ", e);
            UtilJsf.messageError(e);
            return null;
        } catch (Exception e) {
            // Capture toute exception inattendue
            logger.error("Erreur inattendue lors de la mise à jour du compte : ", e);
            UtilJsf.messageError("Erreur inattendue lors du traitement.");
            return null;
        }
    }


    public String supprimer(Compte item) {
        try {
            serviceCompte.supprimer(item.getId());
            liste.remove(item);
            UtilJsf.messageInfo("Suppression effectuée avec succès.");
        } catch (ExceptionValidation e) {
            UtilJsf.messageError(e);
        }
        return null;
    }
}
