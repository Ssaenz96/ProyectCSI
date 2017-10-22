/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.StoreProducts;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Saul Saenz
 * @date 06-10-2017
 * @version 1.0
 */
@Stateless
@LocalBean
public class StoreProductsFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    public List<StoreProducts> findAll(){
        TypedQuery query;
        query =  em.createQuery("SELECT s FROM StoreProducts s", StoreProducts.class);
        return query.getResultList();
    }
    
    public StoreProducts findById(Long id){
        try{
            TypedQuery query;
            query = em.createQuery("SELECT s FROM StoreProducts s WHERE s.storeProductId =:idParam", StoreProducts.class);
            query.setParameter("idParam", id);
            return (StoreProducts) query.getSingleResult();
        }catch(Exception ex){
            return null;
        }
    }
    
    public String insert(StoreProducts storeProducts) {
        em.persist(storeProducts);
        return "StoreProductsList";
    }

    public String update(StoreProducts storeProducts) {
        em.merge(storeProducts);
        return "StoreProductsList";
    }
    public String delete(StoreProducts storeProducts){
        em.remove(em.merge(storeProducts));
        return "StoreProductsList";
    }
}
