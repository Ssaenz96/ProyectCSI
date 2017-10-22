/*
 * RegionsFacade
 *
 * Version 2.0
    Version 2.1(findAll y findById modificados)
 *
 * 5/10/2017
 *
 * Luis Amaury Urbina Molina
 */
package Facade;

import Entity.Regions;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Saul Saenz
 */
@Stateless
@LocalBean
public class RegionsFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    public List<Regions> findAll() {
        TypedQuery query = em.createQuery("SELECT r FROM Regions r", Regions.class);
        return query.getResultList();
    }
    
    public Regions findById(Long id) {
        try {
            TypedQuery query = em.createQuery("SELECT r FROM Regions r WHERE r.regionId =:idParam"
                    , Regions.class);
            query.setParameter("idParam", id);
            return (Regions) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Regions> findByCode(String code) {
        TypedQuery query = em.createQuery("SELECT r FROM Regions r WHERE r.regionCode =:codeParam", Regions.class);
        query.setParameter("codeParam", code);
        return query.getResultList();
        
    }
    
    public String insert(Regions regions) {
        em.persist(regions);
        return "RegionsList";
    }

    public String update(Regions regions) {
        em.merge(regions);
        return "RegionsList";
    }
    public String delete(Regions regions){
        em.remove(em.merge(regions));
        return "RegionsList";
    }
}
