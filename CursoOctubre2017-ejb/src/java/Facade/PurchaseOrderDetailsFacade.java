/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.PurchaseOrderDetails;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Usuario
 * Eduardo Alejandro Martínez Melo (insert, update delete)
 */
@Stateless
@LocalBean
public class PurchaseOrderDetailsFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    private final String pageList = "PurchaseOrderDetailsList";
    
    public List<PurchaseOrderDetails> findAll(){
        TypedQuery query;
        query = em.createQuery("SELECT pod from PurchaseOrderDetails pod", PurchaseOrderDetails.class);
        return query.getResultList();
    }
    
    public PurchaseOrderDetails findById(Long id){
        try{
            TypedQuery query=em.createQuery("SELECT pod from PurchaseOrderDetails pod WHERE pod.pODetailId=:idParam", PurchaseOrderDetails.class);
            query.setParameter("idParam", id);
            return (PurchaseOrderDetails) query.getSingleResult();
        } catch(Exception e){
            return null;
        }
    }
    
    public String insert(PurchaseOrderDetails purchaseOrderDetails) {
        em.persist(purchaseOrderDetails);
        return pageList;
    }
    
    public String update(PurchaseOrderDetails purchaseOrderDetails) {
        em.merge(purchaseOrderDetails);
        return pageList;
    }
    
    public String delete(PurchaseOrderDetails purchaseOrderDetails) {
        em.remove(em.merge(purchaseOrderDetails));
        return pageList;
    }
}
