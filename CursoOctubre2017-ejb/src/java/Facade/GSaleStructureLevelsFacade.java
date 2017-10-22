package Facade;

import Entity.GSaleStructureLevels;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * GSaleStructureLevelsFacade
 * Version 3
 * Fecha 05/10/2017
 * @author Irvin Omar Hernandez Hernandez 
  *      Eduardo Alejandro Martínez Melo (insert, update delete)
 * Agregue findAll y findById 
 */
@Stateless
@LocalBean
public class GSaleStructureLevelsFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private final String pageList = "GSaleStructureLevelsList";
    
    public List<GSaleStructureLevels> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT g from GSaleStructureLevels g", GSaleStructureLevels.class);
        return query.getResultList();
    }

    public GSaleStructureLevels findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT g from GSaleStructureLevels g WHERE g.GSaleStructLevelId =:idParam", GSaleStructureLevels.class);
            query.setParameter("idParam", id);
            return (GSaleStructureLevels) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String insert(GSaleStructureLevels gSaleStructureLevels) {
        em.persist(gSaleStructureLevels);
        return pageList;
    }
    
    public String update(GSaleStructureLevels gSaleStructureLevels) {
        em.merge(gSaleStructureLevels);
        return pageList;
    }
    
    public String delete(GSaleStructureLevels gSaleStructureLevels) {
        em.remove(em.merge(gSaleStructureLevels));
        return pageList;
    }
}
