/*
 * PurchaseOrderStatusFacade
 *
 * Version 2.0
    Version 2.1(findAll y findById modificados)
 *
 * 5/10/2017
 *
 * Luis Amaury Urbina Molina
 */
package Facade;

import Entity.PurchaseOrderStatus;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class PurchaseOrderStatusFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    public String insert(PurchaseOrderStatus purchaseOrderStatus){
        
        em.persist(purchaseOrderStatus);//recibe un parametro de cualquier tipo de objeto y lo agrega como un registro, Persist: solo sirve para agregar registros
        return "PurchaseOrderStatusList";
    }
    
    public String update(PurchaseOrderStatus purchaseOrderStatus){
        
        em.merge(purchaseOrderStatus);//el merge va a mandar el objeto que ya existia y que coincida en ID, si lo encuentra lo reemplaza
        return "PurchaseOrderStatusList";
        
    }
    
    public String delete (PurchaseOrderStatus purchaseOrderStatus){
        em.remove(em.merge(purchaseOrderStatus));
        return "PurchaseOrderStatusList";
        
    }
    
    public List<PurchaseOrderStatus> findAll() {
        TypedQuery query = em.createQuery("SELECT p FROM PurchaseOrderStatus p", PurchaseOrderStatus.class);
        return query.getResultList();
    }
    
    public PurchaseOrderStatus findById(Long id) {
        try {
            TypedQuery query = em.createQuery("SELECT p FROM PurchaseOrderStatus p WHERE p.pOStatusId =:idParam"
                    , PurchaseOrderStatus.class);
            query.setParameter("idParam", id);
            return (PurchaseOrderStatus) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
