
package Facade;

import Entity.Products;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Usuario
 * Eduardo Alejandro Martínez Melo (insert, update delete)
 */
@Stateless
@LocalBean
public class ProductsFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    private final String pageList = "ProductsList";
    
    public List<Products> findAll(){
        TypedQuery query;
        query = em.createQuery("SELECT p from Products p", Products.class);
        return query.getResultList();
    }
    
    public Products findById(Long id){
        try{
            TypedQuery query;
            query = em.createQuery("SELECT p from Products p WHERE p.productId =:idParam", Products.class);
            query.setParameter("idParam", id);
            return (Products)query.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    
    public String insert(Products products) {
        em.persist(products);
        return pageList;
    }
    
    public String update(Products products) {
        em.merge(products);
        return pageList;
    }
    
    public String delete(Products products) {
        em.remove(em.merge(products));
        return pageList;
    }
}
