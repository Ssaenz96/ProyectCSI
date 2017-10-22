package Facade;

import Entity.Channels;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * ChannelsFacade
 * Version 3
 * Fecha 05/10/2017
 * @author Irvin Omar Hernandez Hernandez 
 *         Eduardo Alejandro Martínez Melo (insert, update delete)
 * Agregue findAll y findById 
 */
@Stateless
@LocalBean
public class ChannelsFacade {
    
    private final String pageList = "ChannelsList";
    
    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Channels> findAll() {
        TypedQuery query;
        query = em.createQuery("SELECT c from Channels c", Channels.class);
        return query.getResultList();
    }

    public Channels findById(Long id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT g from Channels g WHERE g.channelId =:idParam", Channels.class);
            query.setParameter("idParam", id);
            return (Channels) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public String insert(Channels channel) {
        em.persist(channel);
        return pageList;
    }
    
    public String update(Channels channel) {
        em.merge(channel);
        return pageList;
    }
    
    public String delete(Channels channel) {
        em.remove(em.merge(channel));
        return pageList;
    }
}