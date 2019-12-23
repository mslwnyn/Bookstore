/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import za.co.tshim.domain.model.BookEntity;
import za.co.tshim.domain.model.BookLanguage;

/**
 *
 * @author F3340791
 */
@Stateless
public class BookLanguageEntityRepositoryBean implements BookLanguageEntityRepositoryBeanLocal {
    
    @PersistenceContext(unitName = "catalogue_0PU")
    private EntityManager entityManager;
    
    @Override
    public List<BookLanguage> findAll() {
      TypedQuery<BookLanguage> query = entityManager.createQuery("from BookLanguage", BookLanguage.class);
      return query.getResultList();
    }
}
