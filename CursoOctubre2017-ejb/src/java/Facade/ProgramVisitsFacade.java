/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.ProgramVisits;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sergio
* Eduardo Alejandro Martínez Melo (insert, update delete)
 * @version 3
 */
@Stateless
@LocalBean
public class ProgramVisitsFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;

    private final String pageList = "ProgramVisitsList";
    
    public List<ProgramVisits> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT pV from ProgramVisits pV", ProgramVisits.class);
        return query.getResultList();
    }

    public ProgramVisits findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT pV from ProgramVisits pV WHERE pV.progVisitId =:idParam", ProgramVisits.class);
            query.setParameter("idParam", id);
            return (ProgramVisits) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String insert(ProgramVisits programVisits) {
        em.persist(programVisits);
        return pageList;
    }
    
    public String update(ProgramVisits programVisits) {
        em.merge(programVisits);
        return pageList;
    }
    
    public String delete(ProgramVisits programVisits) {
        em.remove(em.merge(programVisits));
        return pageList;
    }
}
