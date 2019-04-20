/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intiformation.gestioncentre.webservice;

import com.intiformation.gestioncentre.entity.Etudiant;
import com.intiformation.gestioncentre.facade.EtudiantFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * inplementation d'un webservice rest pour l'étudiant
 *
 * @author IN-MP-007
 */
@Stateless // déclaration du webservice rest comme EJB
@Path("etudiants")// annotation JA_RS prmet de definir l'URL d'acces à la ressource
public class EtudiantRestservice {

    @EJB // instanciation de la facade par le serveur
    private EtudiantFacade etudiantFacade;

    /*===================================Méthodes à exposer au web service=============================*/
    /**
     * méthode qui recup la liste des étudiants de la bdd et les expose dans le
     * ws
     *
     * @return
     */
    @GET
    @Path("liste")
    @Produces("application/xml") // type MIME retourné par la méthode
    public List<Etudiant> getAllEtudiant() {
        return etudiantFacade.findAll();

    }

    /**
     * recup un etudiant via son id
     *
     * @return
     */

    @GET
    @Path("{idEtudiant : [0-9]+}")
   
    public Etudiant getEtudiantById(@PathParam("idEtudiant") int pEtudiantID) {
        return etudiantFacade.findById(pEtudiantID);

    }

    @POST
    @Path("ajouter")
    @Consumes({"application/xml", "application/json"})// type MIME envoyé par le client
    @Produces({"application/xml", "application/json"})

    public Response ajouterEtudiant(Etudiant pEtudiant) {
        // verif de l'étudiant à ajouter
        if (pEtudiant == null) {
            //renvoi d'un message dans la réponse
            return Response.status(400).entity("Veuillez ajouter les details de l'étudiant").build();
        }

        if (pEtudiant.getNom() == null) {
            //renvoi d'un message dans la réponse
            return Response.status(400).entity("Veuillez indiquez le nom de l'étudiant").build();
        }
        //2. ajout de l'étudiant
        etudiantFacade.add(pEtudiant);

        //3. renvoi de la réonse avec un message + status 200
        Response reponse = Response.status(200).entity("Etudiant ajouter avec succès").build();
        return reponse;

    }

    /**
     * permet de modifier un étudiant dans la bdd <br/>
     *
     * @return
     */
    @PUT
    @Path("{idEtudiant}")
    @Consumes({"application/xml", "application/json"})// type MIME envoyé par le client
    @Produces({"application/xml", "application/json"})
    public Response modifierEtudiant(@PathParam("idEtudiant") int etudiantID,
            Etudiant pEtudiant) {

        if (pEtudiant == null) {
            //renvoi d'un message dans la réponse
            return Response.status(400).entity("Veuillez ajouter les details de l'étudiant").build();
        }

        // 2.modif de l'étudiant dans la bdd via la façade
        etudiantFacade.update(pEtudiant);

        //3. renvoi de la réponse : statut 200 + l'étudiant qui va être modifié
        return Response.ok().entity(etudiantFacade.findById(etudiantID)).build();

    }

    /**
     * suppression d'un étudiant dans la BDD
     * méthode appelée avec une requete HTTP DELETE
     * @param etudiantID
     * @param pEtudiant
     * @return 
     */
    @DELETE
    @Path("supprimer/{idEtudiant : [0-9]+}")
    
    @Produces({"text/plain"})
    public Response supprimerEtudiant(@PathParam("idEtudiant") int pEtudiant) {
        
        //1. suppression via la façade
etudiantFacade.delete(etudiantFacade.findById(pEtudiant));

//2. renvoi de la réponse au client ws
 return Response.status(202).entity("tudiant supprimé avec succès").build();
    }

}//fin classe
