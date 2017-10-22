/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.GSaleStructures;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
 * GSaleStructuresFacade
 * Version 2
 * Fecha 05/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 * Agregue findAll y findById 
 */

/**
 * GSaleStructuresFacade
 * Version 2
 * Fecha 10/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 * Funciones botones list
 */
@Stateless
@LocalBean
public class GSaleStructuresFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<GSaleStructures> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT g from GSaleStructures g", GSaleStructures.class);
        return query.getResultList();
    }

    public GSaleStructures findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT g from GSaleStructures g WHERE g.GSaleStructId =:idParam", GSaleStructures.class);
            query.setParameter("idParam", id);
            return (GSaleStructures) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    //----------------funciones para los botones de las listas------------------
    public String insert(GSaleStructures gSaleStructures) {
        this.em.persist(gSaleStructures);
        return "GSaleStructuresList";
    }

    public String update(GSaleStructures gSaleStructures) {
        this.em.merge(gSaleStructures);
        return "GSaleStructuresList";
    }

    public String delete(GSaleStructures gSaleStructures) {
        this.em.remove(this.em.merge(gSaleStructures));
        return "GSaleStructuresList";
    }

}
