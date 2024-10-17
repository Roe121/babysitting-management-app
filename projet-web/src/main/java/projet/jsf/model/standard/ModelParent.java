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

import projet.commun.dto.DtoContrat;
import projet.commun.dto.DtoParent;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceContrat;
import projet.commun.service.IServiceParent;
import projet.jsf.data.Contrat;
import projet.jsf.data.Parent;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelParent implements Serializable {

	//-------
	// Champs
	//-------
	private static final Logger logger = LoggerFactory.getLogger(ModelParent.class);

	private List<Parent>	liste;
	
	private List<Contrat> contratsParent;
	
	private Parent			courant;
	
	@EJB
	private IServiceParent	serviceParent;
	
	@EJB
    private IServiceContrat serviceContrat;
	
	@Inject
	private IMapper			mapper;

	//-------
	// Getters 
	//-------
	
	public List<Parent> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoParent dto : serviceParent.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}
	
	public Parent getCourant() {
		if ( courant == null ) {
			courant = new Parent();
		}
		return courant;
	}
	
	 public List<Contrat> getContratsParent() {
	        if (contratsParent == null && courant != null && courant.getId() != null) {
	            contratsParent = new ArrayList<>();
	            for (DtoContrat dto : serviceContrat.listerParParent(courant.getId())) {
	                contratsParent.add(mapper.map(dto));  // Mapper les DtoContrat vers des objets Contrat
	            }
	        }
	        
//	        logger.info("Contrats pour le parent {} sont {} ", courant.getNom(), contratsParent.toString());
	        
	        return contratsParent;
	    }
	
	//-------
	// Initialisaitons
	//-------
	
	public String actualiserCourant() {
		if ( courant != null ) {
			DtoParent dto = serviceParent.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "Le parent demandé n'existe pas" );
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
				serviceParent.inserer( mapper.map(courant) );
			} else {
				serviceParent.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public String supprimer( Parent item ) {
		try {
			serviceParent.supprimer( item.getId() );
			liste.remove(item);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e ); 
		}
		return null;
	}
	
}
