package Facade;

import Entity.Contacts;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
 * ContactsFacade
 * Version 3
 * Fecha 05/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 *         Eduardo Alejandro Martínez Melo (insert, update delete)
 * Agregue findAll y findById 
 */
@Stateless
@LocalBean
public class ContactsFacade {
    
    private final String pageList = "ContactsList";

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Contacts> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT g from Contacts g", Contacts.class);
        return query.getResultList();
    }

    public Contacts findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT g from Contacts g WHERE g.contactId =:idParam", Contacts.class);
            query.setParameter("idParam", id);
            return (Contacts) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String insert(Contacts contact) {
        em.persist(contact);
        return pageList;
    }
    
    public String update(Contacts contact) {
        em.merge(contact);
        return pageList;
    }
    
    public String delete(Contacts contact) {
        em.remove(em.merge(contact));
        return pageList;
    }
}