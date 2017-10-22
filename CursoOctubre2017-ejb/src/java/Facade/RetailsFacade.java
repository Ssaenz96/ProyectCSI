/*
* Nombre de la clase: RetailsFacade
*
* Información de la versión: version 1
*
* Fecha: 05/10/2017
*
* Copyright: Eduardo Alejandro Martínez Melo
*/

/**
 * RetailsFacade
 * Version 2
 * Fecha 10/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 * funciones botones list 
 */

package Facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import Entity.Retails;

@Stateless
@LocalBean
public class RetailsFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    public List<Retails> findAll() {
        TypedQuery query = em.createQuery("SELECT r FROM Retails r", Retails.class);
        return query.getResultList();
    }
    
    public Retails findById(Long id) {
        try {
            TypedQuery query = em.createQuery("SELECT r FROM Retails r WHERE r.retailId =:idParam",Retails.class);
            query.setParameter("idParam", id);
            return (Retails) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    
    
    //----------------funciones para los botones de las listas------------------
    public String insert(Retails retails) {
        this.em.persist(retails);
        return "RetailsList";
    }

    public String update(Retails retails) {
        this.em.merge(retails);
        return "RetailsList";
    }

    public String delete(Retails retails) {
        this.em.remove(this.em.merge(retails));
        return "RetailsList";
}
    
}
