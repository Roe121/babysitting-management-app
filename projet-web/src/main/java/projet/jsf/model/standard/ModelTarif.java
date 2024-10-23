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

import projet.commun.dto.DtoTarif;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceParent; // Nouveau service pour gérer les parents
import projet.commun.service.IServiceTarif;
import projet.jsf.data.Compte;
import projet.jsf.data.Tarif;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelTarif implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(ModelTarif.class);

	// -------
	// Champs
	// -------

	private List<Tarif> liste;
	private Tarif courant;

	@EJB
	private IServiceTarif serviceTarif;

	@EJB
	private IServiceParent serviceParent; // EJB pour interagir avec les parents

	@Inject
	private IMapper mapper;

	// -------
	// Getters
	// -------

	public List<Tarif> getListe() {
		if (liste == null) {
			liste = new ArrayList<>();
			for (DtoTarif dto : serviceTarif.listerTout()) {
				liste.add(mapper.map(dto));
			}
		}
		return liste;
	}
	
	   public Tarif getCourant() {
	        if (courant == null) {
	            courant = new Tarif();
	        }
	        return courant;
	    }

	// -------
	// Initialisations
	// -------

	public String actualiserCourant() {
		if (courant != null) {
			DtoTarif dto = serviceTarif.retrouver(courant.getId());
			if (dto == null) {
				UtilJsf.messageError("Le Tarif demandé n'existe pas");
				return "test/liste";
			} else {
				courant = mapper.map(dto);
			}
		}
		return null;
	}

	// -------
	// Actions
	// -------

	public String supprimer(Tarif item) {
		try {
			serviceTarif.supprimer(item.getId());
			liste.remove(item);
			UtilJsf.messageInfo("Suppression effectuée avec succès.");
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
		}
		return null;
	}

	public String validerMiseAJour() {
		try {
			if (courant.getId() == null) { // Ajout 
				serviceTarif.inserer(mapper.map(courant));
				UtilJsf.messageInfo("Ajout du tarif effectuée avec succès.");
			} else { // Cas de modification d'un tarif existant
				serviceTarif.modifier(mapper.map(courant));
				UtilJsf.messageInfo("Mise à jour du tarif effectuée avec succès.");
			}

			return "liste"; // Redirige vers la liste après succès
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
}
