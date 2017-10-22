/*
* Nombre de la clase: RetailContacts
*
* Información de la versión: version 1
*
* Fecha: 05/10/2017
*
* Copyright: Eduardo Alejandro Martínez Melo
*/

package Facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import Entity.RetailContacts;

@Stateless
@LocalBean
public class RetailContactsFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    public List<RetailContacts> findAll() {
        TypedQuery query = em.createQuery("SELECT r FROM RetailContacts r", RetailContacts.class);
        return query.getResultList();
    }
    
    public RetailContacts findById(Long id) {
        try {
            TypedQuery query = em.createQuery("SELECT r FROM RetailContacts r WHERE r.retailContactId =:idParam"
                    , RetailContacts.class);
            query.setParameter("idParam", id);
            return (RetailContacts) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
     public String insert(RetailContacts retailContacts) {
        em.persist(retailContacts);
        return "RetailContactsList";
    }

    public String update(RetailContacts retailContacts) {
        em.merge(retailContacts);
        return "RetailContactsList";
    }
    public String delete(RetailContacts retailContacts){
        em.remove(em.merge(retailContacts));
        return "RetailContactsList";
    }
}
