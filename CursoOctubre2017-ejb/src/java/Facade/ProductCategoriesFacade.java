/*
 * ProductCategoriesFacade
 *
 * Version 2.0
 *
 * 10:00am 5/10/17
 *
 * Luis. All Rights Reserved.
 */
package Facade;

import Entity.ProductCategories;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sergio
 * @version 2
 * -----------------------------------------------------------------------------
 * ProductCategoriesFacade
 * Irvin Omar Hernandez Hernandez
 * version 3
 * 17/10/2017
 * funciones edit
 */
@Stateless
@LocalBean
public class ProductCategoriesFacade {

    @PersistenceContext(unitName = "CursoOctubre2017-ejbPU")
    EntityManager em;
    
    public List<ProductCategories> findAll(){
        TypedQuery query;
        query = em.createQuery("SELECT pC from ProductCategories pC", ProductCategories.class);
        return query.getResultList();
    }
    
    public ProductCategories findById(Long id){
        try{
            TypedQuery query;
            query = em.createQuery("SELECT pC from ProductCategories pC WHERE pC.prodCategoryId =:idParam", ProductCategories.class);
            query.setParameter("idParam", id);
            return (ProductCategories) query.getSingleResult();
            
            /*return (ProductCategories) em.createQuery("SELECT pC from ProductCategories pC WHERE pC.prodCategoryId =:idParam", ProductCategories.class)
                    .setParameter("idParam", id)
                    .getSingleResult();*/
            
        }catch(Exception e){
            return null;
        }
    }
    
    
    public String insert(ProductCategories productCategories){
        
        em.persist(productCategories);//recibe un parametro de cualquier tipo de objeto y lo agrega como un registro, Persist: solo sirve para agregar registros
        return "ProductCategoriesList";
    }
    
    public String update(ProductCategories productCategories){
        
        em.merge(productCategories);//el merge va a mandar el objeto que ya existia y que coincida en ID, si lo encuentra lo reemplaza
        return "ProductCategoriesList";
        
    }
    
    public String delete (ProductCategories productCategories){
        em.remove(em.merge(productCategories));
        return "ProductCategoriesList";
        
    }
     //-------------------------------------------------------------------------
    public ProductCategories findByCode(ProductCategories code) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT h from ProductCategories h WHERE h.prodCategoryCode =:paramCode", ProductCategories.class);
            query.setParameter("paramCode", code.getProdCategoryCode());
            return (ProductCategories) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<ProductCategories> edit(ProductCategories code, long Id) {
        try {
            TypedQuery query;
            query = em.createQuery("SELECT h from ProductCategories h WHERE h.prodCategoryCode =:paramCode AND h.prodCategoryId <>:paramId", ProductCategories.class);
            query.setParameter("paramCode", code.getProdCategoryCode());
            query.setParameter("paramId", code.getProdCategoryId());
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
