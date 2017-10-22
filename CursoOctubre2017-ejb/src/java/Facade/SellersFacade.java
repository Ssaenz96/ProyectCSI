/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Sellers;
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
public class SellersFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    public List<Sellers> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT s from Sellers s", Sellers.class);
        return query.getResultList();
    }

    public Sellers findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT s FROM Sellers s WHERE s.sellerId =:idParam", Sellers.class);
            query.setParameter("idParam", id);
            return (Sellers) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    //Thania López query para encontrar que # de nomina
     public Sellers findByPayRollNumber(String nomina) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT s FROM Sellers s WHERE s.payRollNumber =:payRollNumberParam", Sellers.class);
            query.setParameter("payRollNumberParam", nomina);
            return (Sellers) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
     
     public List<Sellers> edit(Sellers code, long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT s from Sellers s WHERE s.payRollNumber =:nominaParam AND s.sellerId <>:paramId", Sellers.class);
            query.setParameter("nominaParam", code.getPayRollNumber());
            query.setParameter("paramId", code.getSellerId());
            return query.getResultList();
        } catch (Exception e) {
            return null; 
        }
    }
    
    public String insert(Sellers sellers) {
        em.persist(sellers);
        return "SellersList";
    }
    
    public String update(Sellers sellers) {
        em.merge(sellers);
        return "SellersList";
    }
    
    public String delete(Sellers sellers) {
        em.remove(em.merge(sellers));
        return "SellersList";
    }
}
