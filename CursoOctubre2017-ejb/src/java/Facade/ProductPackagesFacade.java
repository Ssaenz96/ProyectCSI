
package Facade;

import Entity.ProductPackages;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sergio
 * Eduardo Alejandro Martínez Melo (insert, update delete)
 * @version 3
 */
@Stateless
@LocalBean
public class ProductPackagesFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    private final String pageList = "ProductPackagesList";
    
    public List<ProductPackages> findAll(){
        TypedQuery query;
        query = em.createQuery("SELECT pP from ProductPackages pP", ProductPackages.class);
        return query.getResultList();
    }
    
    public ProductPackages findById(Long id){
        try{
            TypedQuery query;
            query = em.createQuery("SELECT pP from ProductPackages pP WHERE pP.productPackId =:idParam", ProductPackages.class);
            query.setParameter("idParam", id);
            return (ProductPackages)query.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    
    public String insert(ProductPackages productPackages) {
        em.persist(productPackages);
        return pageList;
    }
    
    public String update(ProductPackages productPackages) {
        em.merge(productPackages);
        return pageList;
    }
    
    public String delete(ProductPackages productPackages) {
        em.remove(em.merge(productPackages));
        return pageList;
    }    
    
}
