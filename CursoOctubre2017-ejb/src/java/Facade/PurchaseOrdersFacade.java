/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.PurchaseOrders;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * PurchaseOrdersFacade
 * Version 2
 * Fecha 06/10/2017
 * @author Irvin Omar Hernandez Hernandez  
 */
/**
 * PurchaseOrdersFacade
 * Version 3 (Corrección en Query find all)
 * Fecha 09/10/2017
 * @author Luis Gerardo Garcia Zárate
 */
@Stateless
@LocalBean
public class PurchaseOrdersFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    public List<PurchaseOrders> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT g from PurchaseOrders g", PurchaseOrders.class);
        return query.getResultList();
    }

    public PurchaseOrders findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT g from PurchaseOrders g WHERE g.POId =:idParam", PurchaseOrders.class);
            query.setParameter("idParam", id);
            return (PurchaseOrders) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    public String insert(PurchaseOrders purchaseOrders) {
        em.persist(purchaseOrders);
        return "PurchaseOrdersList";
    }

    public String update(PurchaseOrders purchaseOrders) {
        em.merge(purchaseOrders);
        return "PurchaseOrdersList";
    }
    public String delete(PurchaseOrders purchaseOrders){
        em.remove(em.merge(purchaseOrders));
        return "PurchaseOrdersList";
    }
}
