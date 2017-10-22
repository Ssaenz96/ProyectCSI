/*
 * MetropolitanZonesFacade
 *
 * Version 3.0
 *
 * 10:00am 5/10/17
 *
 * Luis. All Rights Reserved.
 * Eduardo Alejandro Martínez Melo (insert, update delete)
 */
package Facade;

import Entity.MetropolitanZones;
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
public class MetropolitanZonesFacade {
    
    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;

    private final String pageList = "MetropolitanZonesList";
    
    public List<MetropolitanZones> findAll(){
        TypedQuery query;
        query = em.createQuery("SELECT m from MetropolitanZones m", MetropolitanZones.class);
        return query.getResultList();
    }
    
    public MetropolitanZones findById(Long id){
        try{
            TypedQuery query=em.createQuery("SELECT m from MetropolitanZones m WHERE m.metroZoneId =:idParam", MetropolitanZones.class);
            query.setParameter("idParam", id);
            return (MetropolitanZones) query.getSingleResult();
        } catch(Exception e){
            return null;
        }
    }
    
    public String insert(MetropolitanZones metropolitanZones) {
        em.persist(metropolitanZones);
        return pageList;
    }
    
    public String update(MetropolitanZones metropolitanZones) {
        em.merge(metropolitanZones);
        return pageList;
    }
    
    public String delete(MetropolitanZones metropolitanZones) {
        em.remove(em.merge(metropolitanZones));
        return pageList;
    }
    
    
} 
