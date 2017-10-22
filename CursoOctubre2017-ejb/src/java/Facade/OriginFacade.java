/*
 * OriginFacade
 *
 * Version 2.0
 *
 * 10:00am 5/10/17
 *
 * Luis. All Rights Reserved.
 */
package Facade;

import Entity.Origin;
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
public class OriginFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;

    public List<Origin> findAll(){
        TypedQuery query;
        query = em.createQuery("SELECT o from Origin o", Origin.class);
        return query.getResultList();
    }
    
    public Origin findById(Long id){
        try{
            TypedQuery query=em.createQuery("SELECT o from Origin o WHERE o.originId =:idParam", Origin.class);
            query.setParameter("idParam", id);
            return (Origin) query.getSingleResult();
        } catch(Exception e){
            return null;
        }
    }
    
    public String insert(Origin origin){
        
        em.persist(origin);//recibe un parametro de cualquier tipo de objeto y lo agrega como un registro, Persist: solo sirve para agregar registros
        return "OriginList";
    }
    
    public String update(Origin origin){
        
        em.merge(origin);//el merge va a mandar el objeto que ya existia y que coincida en ID, si lo encuentra lo reemplaza
        return "OriginList";
        
    }
    
    public String delete (Origin origin){
        em.remove(em.merge(origin));
        return "OriginList";
        
    }
}
