/*
 * RetailSaleUnitsFacade
 *
 * Version 2.0
    Version 2.1(findAll y findById modificados)
 *
 * 5/10/2017
 *
 * Luis Amaury Urbina Molina
 */
package Facade;

import Entity.RetailSaleUnits;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class RetailSaleUnitsFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;

    public List<RetailSaleUnits> findAll() {
        TypedQuery query = em.createQuery("SELECT r FROM RetailSaleUnits r", RetailSaleUnits.class);
        return query.getResultList();
    }
    
    public RetailSaleUnits findById(Long id) {
        try {
            TypedQuery query = em.createQuery("SELECT r FROM RetailSaleUnits r WHERE r.retailSUId =:idParam"
                    , RetailSaleUnits.class);
            query.setParameter("idParam", id);
            return (RetailSaleUnits) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public RetailSaleUnits findByRetailSUCode(String code){
        try {
            TypedQuery query;
            query = em.createQuery("SELECT r from RetailSaleUnits r WHERE r.retailSUCode =:codeParam", RetailSaleUnits.class);
            query.setParameter("codeParam", code);
            return (RetailSaleUnits) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<RetailSaleUnits> edit(RetailSaleUnits code, long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT r from RetailSaleUnits r WHERE r.retailSUCode =:codeParam AND r.retailSUId <>:paramId", RetailSaleUnits.class);
            query.setParameter("codeParam", code.getRetailSUCode());
            query.setParameter("paramId", code.getRetailSUId());
            return query.getResultList();
        } catch (Exception e) {
            return null; 
        }
    }
    
    public String insert(RetailSaleUnits retailSaleUnits) {
        em.persist(retailSaleUnits);
        return "RetailSaleUnitsList";
    }

    public String update(RetailSaleUnits retailSaleUnits) {
        em.merge(retailSaleUnits);
        return "RetailSaleUnitsList";
    }
    public String delete(RetailSaleUnits retailSaleUnits){
        em.remove(em.merge(retailSaleUnits));
        return "RetailSaleUnitsList";
    }
}
