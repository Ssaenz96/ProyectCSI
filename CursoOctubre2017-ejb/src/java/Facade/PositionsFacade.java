/*
 * PositionsFacadeFacade
 *
 * Version 2.0
 *
 * 10:00am 5/10/17
 *
 * Luis. All Rights Reserved.
 */
package Facade;

import Entity.Positions;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Carlos Alberto López Solis
 */
@Stateless
@LocalBean
public class PositionsFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    public List<Positions> findAll(){
        TypedQuery query;
        query = em.createQuery("SELECT p from Positions p", Positions.class);
        return query.getResultList();
    }
    
    public Positions findById(Long id){
        try{
            TypedQuery query=em.createQuery("SELECT p from Positions p WHERE p.positionId =:idParam", Positions.class);
            query.setParameter("idParam", id);
            return (Positions) query.getSingleResult();
        } catch(Exception e){
            return null;
        }
    }
      public String insert(Positions positions) {
        em.persist(positions);
        return "PositionsList";
    }
    public String update(Positions positions) {
        em.merge(positions);
        return "PositionsList";
    }
    public String delete(Positions positions){
        em.remove(em.merge(positions));
        return "PositionsList";
    }
}
