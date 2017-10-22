/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Tablas;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Usuario6
 */
@Stateless
@LocalBean
public class TablasFacade {

    private final String pageList = "TablasList";
    
    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em; 
    
    public List<Tablas> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT c FROM Tablas c", Tablas.class);
        return query.getResultList();
    }

    public Tablas findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT t FROM Tablas t WHERE t.tablasId =:idParam", Tablas.class);
            query.setParameter("idParam", id);
            return (Tablas) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String insert(Tablas tablas) {
        em.persist(tablas);
        return pageList;
    }
    
    public String update(Tablas tablas) {
        em.merge(tablas);
        return pageList;
    }
    
    public String delete(Tablas tablas) {
        em.remove(em.merge(tablas));
        return pageList;
    }
}
