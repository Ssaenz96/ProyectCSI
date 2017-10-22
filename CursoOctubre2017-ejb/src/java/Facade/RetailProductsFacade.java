/*
 * @author Gerardo Cardenas
 * Clase: RetailProductsFacade
 * Version 1.0
 * Fecha: 06/10/2017
 * Copyright
 */
package Facade;


import Entity.RetailProducts;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
 * @author Irvin Omar Hernandez Hernandez
 * Clase: RetailProductsFacade
 * Version 1.0
 * Fecha: 13/10/2017
 * Copyright
 */


@Stateless
@LocalBean
public class RetailProductsFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    public List<RetailProducts> findAll(){
        TypedQuery query = em.createQuery("SELECT r FROM RetailProducts r", RetailProducts.class);
        return query.getResultList();
    }
    
    public RetailProducts findById(Long id){
        try{
            TypedQuery query;
            query = em.createQuery("SELECT rP from RetailProducts rP WHERE rP.retailProductId =:idParam", RetailProducts.class);
            query.setParameter("idParam", id);
            return (RetailProducts)query.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    public String insert(RetailProducts retailProducts) {
        em.persist(retailProducts);
        return "RetailProductsList";
    }

    public String update(RetailProducts retailProducts) {
        em.merge(retailProducts);
        return "RetailProductsList";
    }
    public String delete(RetailProducts retailProducts){
        em.remove(em.merge(retailProducts));
        return "RetailProductsList";
    }
    //--------------------------------------------------------------------------
    public RetailProducts findByCode(RetailProducts code) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT h from RetailProducts h WHERE h.skuCode =:paramCode", RetailProducts.class);
            query.setParameter("paramCode", code.getSkuCode());
            return (RetailProducts) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<RetailProducts> edit(RetailProducts code, long Id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT h from RetailProducts h WHERE h.skuCode =:paramCode AND h.retailProductId <>:paramId", RetailProducts.class);
            query.setParameter("paramCode", code.getSkuCode());
            query.setParameter("paramId", code.getRetailProductId());
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    
}
