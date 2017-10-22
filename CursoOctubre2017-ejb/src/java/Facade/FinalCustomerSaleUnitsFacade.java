/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.FinalCustomerSaleUnits;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * FinalCostumerSaleUnitsFacade
 * Version 2
 * Fecha 05/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 * Agregue findAll y findById 
 */
@Stateless
@LocalBean
public class FinalCustomerSaleUnitsFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
public List<FinalCustomerSaleUnits> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT g from FinalCustomerSaleUnits g", FinalCustomerSaleUnits.class);
        return query.getResultList();
    }

public FinalCustomerSaleUnits findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT g from FinalCustomerSaleUnits g WHERE g.customerSaleUnitId =:idParam", FinalCustomerSaleUnits.class);
            query.setParameter("idParam", id);
            return (FinalCustomerSaleUnits) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

//----------------funciones para los botones de las listas------------------
    public String insert(FinalCustomerSaleUnits finalCustomerSaleUnits) {
        this.em.persist(finalCustomerSaleUnits);
        return "FinalCustomerSaleUnitsList";
    }

    public String update(FinalCustomerSaleUnits finalCustomerSaleUnits) {
        this.em.merge(finalCustomerSaleUnits);
        return "FinalCustomerSaleUnitsList";
    }

    public String delete(FinalCustomerSaleUnits finalCustomerSaleUnits) {
        this.em.remove(this.em.merge(finalCustomerSaleUnits));
        return "FinalCustomerSaleUnitsList";
}

}
