/*
 * TradeMarksFacade
 *
 * Version 2.0
    Version 2.1(findAll y findById agregados)
 *
 * 5/10/2017
 *
 * Luis Amaury Urbina Molina
 */
package Facade;

import Entity.TradeMarks;
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
public class TradeMarksFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
        public List<TradeMarks> findAll(){
        TypedQuery query;
        query = em.createQuery("SELECT p from TradeMarks p", TradeMarks.class);
        return query.getResultList();
    }
    
    public TradeMarks findById(Long id){
        try{
            TypedQuery query=em.createQuery("SELECT p from TradeMarks p WHERE p.tradeMarkId=:idParam", TradeMarks.class);
            query.setParameter("idParam", id);
            return (TradeMarks) query.getSingleResult();
        } catch(Exception e){
            return null;
        }
    }
    
    public TradeMarks findByTradeMarksCode(String code){
        try{
            TypedQuery query=em.createQuery("SELECT p from TradeMarks p WHERE p.tradeMarkCode=:codeParam", TradeMarks.class);
            query.setParameter("codeParam", code);
            return (TradeMarks) query.getSingleResult();
        } catch(Exception e){
            return null;
        }
    }
    
    public List<TradeMarks> edit(TradeMarks code, long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT s from TradeMarks s WHERE s.tradeMarkCode =:codeParam AND s.tradeMarkId <>:paramId", TradeMarks.class);
            query.setParameter("codeParam", code.getTradeMarkCode());
            query.setParameter("paramId", code.getTradeMarkId());
            return query.getResultList();
        } catch (Exception e) {
            return null; 
        }
    }
    
    public String insert(TradeMarks tradeMarks) {
        em.persist(tradeMarks);
        return "TradeMarksList";
    }

    public String update(TradeMarks tradeMarks) {
        em.merge(tradeMarks);
        return "TradeMarksList";
    }
    public String delete(TradeMarks tradeMarks){
        em.remove(em.merge(tradeMarks));
        return "TradeMarksList";
    }
}
