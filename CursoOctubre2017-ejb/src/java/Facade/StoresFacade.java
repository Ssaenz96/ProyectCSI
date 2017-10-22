/*
 * StoresFacade
 *
 * Version 1.0
 *
 * 9:00am 6/10/17
 *
 * @author Carlos Alberto López Solis
 */
package Facade;

import Entity.Stores;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
@LocalBean
public class StoresFacade {
    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;

    public List<Stores> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT s from Stores s", Stores.class);
        return query.getResultList();
    }
    
    public Stores findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT s from Stores s WHERE s.storeId =:idParam", Stores.class);
            query.setParameter("idParam", id);
            return (Stores) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Stores findByStoreCode(String code) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT s from Stores s WHERE s.storeCode =:codeParam", Stores.class);
            query.setParameter("codeParam", code);
            return (Stores) query.getSingleResult();
        } catch (Exception e) {
            return null; 
        }
    }
    
    public List<Stores> edit(Stores code, long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT s from Stores s WHERE s.storeCode =:codeParam AND s.storeId <>:paramId", Stores.class);
            query.setParameter("codeParam", code.getStoreCode());
            query.setParameter("paramId", code.getStoreId());
            return query.getResultList();
        } catch (Exception e) {
            return null; 
        }
    }
    
     public String insert(Stores stores) {
        em.persist(stores);
        return "StoresList";
    }

    public String update(Stores stores) {
        em.merge(stores);
        return "StoresList";
    }
    public String delete(Stores stores){
        em.remove(em.merge(stores));
        return "StoresList";
    }
}
