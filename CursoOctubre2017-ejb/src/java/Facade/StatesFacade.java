
package Facade;

import Entity.States;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
 * StatesFacade
 * Version 2
 * Fecha 05/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 * Agregue findAll y findById   
 */

/*
* Nombre de la clase: StatesFacade
* Información de la versión: V. 3.0 (Queries para regla de negocio)
* Fecha: 16/10/2017
* Copyright: Jesús Rey de la Cruz Arriaga
*/

@Stateless
@LocalBean
public class StatesFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    public List<States> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT s from States s", States.class);
        return query.getResultList();
    }
 //****Queries para regla de negocio (No States repetidos)     
    public List<States> findByCode(String stateCode){
        TypedQuery query;
        query = em.createQuery("SELECT s from States s WHERE s.stateCode =:paramStateCode ", States.class);
        query.setParameter("paramStateCode", stateCode);
        return query.getResultList();
    }
    public List<States> findByName(String name){
        TypedQuery query;
        query = em.createQuery("SELECT s from States s WHERE s.name =:paramName", States.class);
        query.setParameter("paramName", name);
        return query.getResultList();
    }
     public List<States> findByCodeEdit(String stateCode, Long id){
        TypedQuery query;
        query = em.createQuery("SELECT s from States s WHERE s.stateCode =:paramStateCode AND s.stateId <>:paramId", States.class);
        query.setParameter("paramStateCode", stateCode);
        query.setParameter("paramId", id);
        return query.getResultList();
    }
    public List<States> findByNameEdit(String name, Long id){
        TypedQuery query;
        query = em.createQuery("SELECT s from States s WHERE s.name =:paramName AND s.stateId <>:paramId", States.class);
        query.setParameter("paramName", name);
        query.setParameter("paramId", id);
        return query.getResultList();
    }
  //*********************************************************** 

    public States findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT s from States s WHERE s.stateId =:idParam", States.class);
            query.setParameter("idParam", id);
            return (States) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String insert(States states) {
        em.persist(states);
        return "StatesList";
    }

    public String update(States states) {
        em.merge(states);
        return "StatesList";
    }
    public String delete(States states){
        em.remove(em.merge(states));
        return "StatesList";
    }
}
