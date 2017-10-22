/*
 * StoreRanksFacade
 *
 * Version 2.0
    Version 2.1(findAll y findById agregados)
 *
 * 5/10/2017
 *
 * Luis Amaury Urbina Molina
 */
package Facade;

import Entity.StoreRanks;
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
public class StoreRanksFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    public List<StoreRanks> findAll(){
        TypedQuery query;
        query = em.createQuery("SELECT p from StoreRanks p", StoreRanks.class);
        return query.getResultList();
    }
    
    public StoreRanks findById(Long id){
        try{
            TypedQuery query=em.createQuery("SELECT p from StoreRanks p WHERE p.storeRankId=:idParam", StoreRanks.class);
            query.setParameter("idParam", id);
            return (StoreRanks) query.getSingleResult();
        } catch(Exception e){
            return null;
        }
    }
    
    public StoreRanks findByStoreRankCode(String code){
        try{
            TypedQuery query=em.createQuery("SELECT p from StoreRanks p WHERE p.storeRankCode =:codeParam", StoreRanks.class);
            query.setParameter("codeParam", code);
            return (StoreRanks) query.getSingleResult();
        } catch(Exception e){
            return null;
        }
    }
    
    public List<StoreRanks> edit(StoreRanks code, long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT s from StoreRanks s WHERE s.storeRankCode =:codeParam AND s.storeRankId <>:paramId", StoreRanks.class);
            query.setParameter("codeParam", code.getStoreRankCode());
            query.setParameter("paramId", code.getStoreRankId());
            return query.getResultList();
        } catch (Exception e) {
            return null; 
        }
    }

    
    public String insert(StoreRanks storeRanks) {
        em.persist(storeRanks);
        return "StoreRanksList";
    }

    public String update(StoreRanks storeRanks) {
        em.merge(storeRanks);
        return "StoreRanksList";
    }
    public String delete(StoreRanks storeRanks){
        em.remove(em.merge(storeRanks));
        return "StoreRanksList";
    }
}
