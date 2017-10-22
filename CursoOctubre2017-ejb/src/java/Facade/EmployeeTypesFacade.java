/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.EmployeeTypes;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
 * EmployeeTypesFacade
 * Version 2
 * Fecha 05/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 * Agregue findAll y findById 
 */

@Stateless
@LocalBean
public class EmployeeTypesFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<EmployeeTypes> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT g from EmployeeTypes g", EmployeeTypes.class);
        return query.getResultList();
    }

    public EmployeeTypes findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT g from EmployeeTypes g WHERE g.employeeTypeId =:idParam", EmployeeTypes.class);
            query.setParameter("idParam", id);
            return (EmployeeTypes) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String insert(EmployeeTypes employeeTypes) {
        em.persist(employeeTypes);
        return "EmployeeTypesList";
    }

    public String update(EmployeeTypes employeeTypes) {
        em.merge(employeeTypes);
        return "EmployeeTypesList";
    }
    public String delete(EmployeeTypes employeeTypes){
        em.remove(em.merge(employeeTypes));
        return "EmployeeTypesList";
    }
}
