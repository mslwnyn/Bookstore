/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.repository;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import za.co.tshim.domain.model.Category;

/**
 *
 * @author F3340791
 */

//@EJB(name = "categoryRepositoryBean", beanInterface = CategoryEntityRepositoryBeanLocal.class,beanName = "categoryBean")
@Stateless
public class CategoryEntityRepositoryBean implements CategoryEntityRepositoryBeanLocal,Serializable {
                     
    @PersistenceContext(unitName = "catalogue_0PU")
    private EntityManager entityManager;

    @Override
    public List<Category> findAll() {
        TypedQuery<Category> query = entityManager.createQuery("from Category", Category.class);        
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
}
