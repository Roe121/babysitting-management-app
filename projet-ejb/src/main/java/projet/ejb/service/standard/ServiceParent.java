package projet.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import projet.commun.dto.DtoParent;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceParent;
import projet.ejb.dao.IDaoParent;
import projet.ejb.data.Parent;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceParent implements IServiceParent {

    // Champs
    @Inject
    private IMapperEjb mapper;
    @Inject
    private IDaoParent daoParent;

    // Actions

    @Override
    public int inserer(DtoParent dtoParent) throws ExceptionValidation {
        verifierValiditeDonnees(dtoParent);
        int id = daoParent.inserer(mapper.map(dtoParent));
        return id;
    }

    @Override
    public void modifier(DtoParent dtoParent) throws ExceptionValidation {
        verifierValiditeDonnees(dtoParent);
        daoParent.modifier(mapper.map(dtoParent));
    }

    @Override
    public void supprimer(int idParent) throws ExceptionValidation {
        daoParent.supprimer(idParent);
    }

    @Override
    @TransactionAttribute(NOT_SUPPORTED)
    public DtoParent retrouver(int idParent) {
        return mapper.map(daoParent.retrouver(idParent));
    }

    @Override
    @TransactionAttribute(NOT_SUPPORTED)
    public List<DtoParent> listerTout() {
        List<DtoParent> liste = new ArrayList<>();
        for (Parent parent : daoParent.listerTout()) {
            liste.add(mapper.map(parent));
        }
        return liste;
    }
    
    public DtoParent retrouverParCompteId(Integer compteId) {
        // Effectuer une requête pour rechercher un parent lié au compte
        return mapper.map(daoParent.retrouverParCompteId(compteId));
    }

    // Méthodes auxiliaires

    private void verifierValiditeDonnees(DtoParent dtoParent) throws ExceptionValidation {

        StringBuilder message = new StringBuilder();

        if (dtoParent.getNom() == null || dtoParent.getNom().isEmpty()) {
            message.append("\nLe nom est absent.");
        } else if (dtoParent.getNom().length() < 3) {
            message.append("\nLe nom est trop court.");
        } else if (dtoParent.getNom().length() > 50) {
            message.append("\nLe nom est trop long.");
        }

        if (dtoParent.getPrenom() == null || dtoParent.getPrenom().isEmpty()) {
            message.append("\nLe prénom est absent.");
        } else if (dtoParent.getPrenom().length() < 3) {
            message.append("\nLe prénom est trop court.");
        } else if (dtoParent.getPrenom().length() > 50) {
            message.append("\nLe prénom est trop long.");
        }

        if (dtoParent.getAdressePostale() == null || dtoParent.getAdressePostale().isEmpty()) {
            message.append("\nL'adresse postale est absente.");
        } else if (dtoParent.getAdressePostale().length() > 255) {
            message.append("\nL'adresse postale est trop longue.");
        }

        if (dtoParent.getAdresseEmail() == null || dtoParent.getAdresseEmail().isEmpty()) {
            message.append("\nL'adresse e-mail est absente.");
        } else if (!dtoParent.getAdresseEmail().contains("@")) {
            message.append("\nL'adresse e-mail est invalide.");
        } else if (dtoParent.getAdresseEmail().length() > 255) {
            message.append("\nL'adresse e-mail est trop longue.");
        }

        if (dtoParent.getTelephone() == null || dtoParent.getTelephone().isEmpty()) {
            message.append("\nLe numéro de téléphone est absent.");
        } else if (dtoParent.getTelephone().length() > 15) {
            message.append("\nLe numéro de téléphone est trop long.");
        }

        if (message.length() > 0) {
            throw new ExceptionValidation(message.toString().substring(1));
        }
    }

}
