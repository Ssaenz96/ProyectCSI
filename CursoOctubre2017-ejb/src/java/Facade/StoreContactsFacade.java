/*
 * StoreContactsFacade
 *
 * Version 1.0
 *
 * 9:00am 6/10/17
 *
 * @author Carlos Alberto López Solis
 */

 /*
* Nombre de la clase: StoreContactsFacade
* Información de la versión: V. 3.0 (Queries para regla de negocio)
* Fecha: 16/10/2017
* Copyright: Jesús Rey de la Cruz Arriaga
 */
package Facade;

import Entity.StoreContacts;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class StoreContactsFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;

    public List<StoreContacts> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT s from StoreContacts s", StoreContacts.class);
        return query.getResultList();
    }
//****Queries para regla de negocio (No StoreContacts repetidos)  

    public List<StoreContacts> findByStoreAndContact(Long contact) {
        TypedQuery query;
        query = em.createQuery("SELECT s FROM StoreContacts s WHERE  s.contacts.contactId =:paramContacts", StoreContacts.class);
        query.setParameter("paramContacts", contact);
        return query.getResultList();
    }

    public List<StoreContacts> findByStoreAndContactAndID( Long contact, Long storeContact) {
        TypedQuery query;
        query = em.createQuery("SELECT s FROM StoreContacts s WHERE  s.contacts.contactId =:paramContacts AND s.storeContacsId <>:paramStoreContact", StoreContacts.class);
        
        query.setParameter("paramContacts", contact);
        query.setParameter("paramStoreContact", storeContact);
        return query.getResultList();
    }
//*****************************************************************************  

    public StoreContacts findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT s from StoreContacts s WHERE s.storeContacsId =:idParam", StoreContacts.class);
            query.setParameter("idParam", id);
            return (StoreContacts) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public String insert(StoreContacts storeContacts) {
        em.persist(storeContacts);//recibe un parametro de cualquier tipo de objeto y lo agrega como un registro, Persist: solo sirve para agregar registros
        return "StoreContactsList";
    }

    public String update(StoreContacts storeContacts) {
        em.merge(storeContacts);//el merge va a mandar el objeto que ya existia y que coincida en ID, si lo encuentra lo reemplaza
        return "StoreContactsList";
    }

    public String delete(StoreContacts storeContacts) {
        em.remove(em.merge(storeContacts));
        return "StoreContactsList";
    }

}
