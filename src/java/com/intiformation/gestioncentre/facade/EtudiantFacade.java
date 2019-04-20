/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intiformation.gestioncentre.facade;

import com.intiformation.gestioncentre.entity.Etudiant;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * couche d'accès aux données (equivaut au DAO
 *
 * @author IN-MP-007
 */

@Stateless // déclaration de la facade comme un EJB
public class EtudiantFacade extends AbstractFacade<Etudiant> {

    @PersistenceContext(unitName = "pu_db_gestion_centre_rest")
    private EntityManager entityManager;

    /**
     * ctor chargé pour initialiser l'étudiant
     */
    public EtudiantFacade() { // on supprime l'argument généré automatiquement pour le remplaver par l'étudiant
        super(Etudiant.class);
    }

    /**
     * redéfintion de la méthode de la classe AbstractFacade <br/>
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    //=====================================================================================//
    //==================méthode spécifiques à l'entité étudiant============================//
    //=====================================================================================//
    //exemple de méthodes : isExist avec nom /prenom
}


