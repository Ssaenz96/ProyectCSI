package Facade;

import Entity.Cities;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
 * ChannelsFacade
 * Version 3
 * Fecha 05/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 *         Eduardo Alejandro Martínez Melo (insert, update delete)
 * Agregue findAll y findById   
 */
@Stateless
@LocalBean
public class CitiesFacade {

    private final String pageList = "CitiesList";
    
    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Cities> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT c FROM Cities c", Cities.class);
        return query.getResultList();
    }

    public Cities findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT c FROM Cities c WHERE c.cityId =:idParam", Cities.class);
            query.setParameter("idParam", id);
            return (Cities) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String insert(Cities cities) {
        em.persist(cities);
        return pageList;
    }
    
    public String update(Cities cities) {
        em.merge(cities);
        return pageList;
    }
    
    public String delete(Cities cities) {
        em.remove(em.merge(cities));
        return pageList;
    }
}
