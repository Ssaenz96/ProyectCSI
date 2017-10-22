/*
 * GroupsFacade
 *
 * Version 3.0
 *
 * 10:00am 5/10/17
 *
 * Luis. All Rights Reserved.
 * Eduardo Alejandro Martínez Melo (insert, update delete)
 */
package Facade;

import Entity.Groups;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class GroupsFacade {
    
    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
  
    private final String pageList = "GroupsList";
    
/*
 * EmployeeTypesFacade
 * Version 3
 * Fecha 05/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 * Agregue findAll y findById
 * Query para ver si se repiten los GroupCode
 */
    public List<Groups> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT g from Groups g", Groups.class);
        return query.getResultList();
    }

    public Groups findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT g from Groups g WHERE g.groupId =:idParam", Groups.class);
            query.setParameter("idParam", id);
            return (Groups) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String login(String usuario, String contraseña){
        try{
            TypedQuery query;
            query = em.createQuery("SELECT g from Groups g Where g.userName =:usuarioParam AND g.password =:contraseñaParam", Groups.class);
            query.setParameter("usuarioParam", usuario);
            query.setParameter("contraseñaParam", contraseña);
            if((Groups) query.getSingleResult() != null){
                return "index";
            }else{
                return "login";
            } 
        }catch (Exception e){
            return "login";
        }
    }
    
    public String insert(Groups groups) {
        em.persist(groups);
        return pageList;
    }
    
    public String update(Groups groups) {
        em.merge(groups);
        return pageList;
    }
    
    public String delete(Groups groups) {
        em.remove(em.merge(groups));
        return pageList;
    }
    public List<Groups> findByUserName(String username){
        TypedQuery query;
        query = em.createQuery("SELECT g from Groups g WHERE g.userName =:usernameparam", Groups.class);
        query.setParameter("usernameparam", username);
        return query.getResultList();
    }
    
    //--------------------------------------------------------------------------
    public Groups findByCode(Groups code) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT h from Groups h WHERE h.groupCode =:paramCode", Groups.class);
            query.setParameter("paramCode", code.getGroupCode());
            return (Groups) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Groups> editUserName(Groups code, long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT g from Groups g WHERE g.userName =:codeParam AND g.groupId <>:paramId", Groups.class);
            query.setParameter("codeParam", code.getUserName());
            query.setParameter("paramId", code.getGroupId());
            return query.getResultList();
        } catch (Exception e) {
            return null; 
        }
    }
    
    public List<Groups> editAlternativeUsername(Groups code, long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT g from Groups g WHERE g.alternateUserName =:codeParam AND g.groupId <>:paramId", Groups.class);
            query.setParameter("codeParam", code.getAlternateUserName());
            query.setParameter("paramId", code.getGroupId());
            return query.getResultList();
        } catch (Exception e) {
            return null; 
        }
    }
    
}
