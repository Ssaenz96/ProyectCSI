/*
 * TaxesFacade
 *
 * Version 2.0
    Version 2.1(findAll y findById agregados)
Version 3.0(findallByCode agregado) <- Luis Garcia
 *
 * 13/10/2017
 *
 * Luis Amaury Urbina Molina
 */
package Facade;

import Entity.Taxes;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Usuario7
 */
@Stateless
@LocalBean
public class TaxesFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
        public List<Taxes> findAll(){
        TypedQuery query;
        query = em.createQuery("SELECT p from Taxes p", Taxes.class);
        return query.getResultList();
    }
    
    public Taxes findById(Long id){
        try{
            TypedQuery query=em.createQuery("SELECT p from Taxes p WHERE p.taxId=:idParam", Taxes.class);
            query.setParameter("idParam", id);
            return (Taxes) query.getSingleResult();
        } catch(Exception e){
            return null;
        }
    }
    
       public Taxes findByTaxCode(String code) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT t from Taxes t WHERE t.taxCode =:codeParam", Taxes.class);
            query.setParameter("codeParam", code);
            return (Taxes) query.getSingleResult();
        } catch (Exception e) {
            return null; 
        }
    }
    
    public List<Taxes> edit(Taxes code, long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT t from Taxes t WHERE t.taxCode =:codeParam AND t.taxId <>:paramId", Taxes.class);
            query.setParameter("codeParam", code.getTaxCode());
            query.setParameter("paramId", code.getTaxId());
            return query.getResultList();
        } catch (Exception e) {
            return null; 
        }
    }
    
    
    public String insert(Taxes taxes) {
        em.persist(taxes);
        return "TaxesList";
    }

    public String update(Taxes taxes) {
        em.merge(taxes);
        return "TaxesList";
    }
    public String delete(Taxes taxes){
        em.remove(em.merge(taxes));
        return "TaxesList";
    }
}
