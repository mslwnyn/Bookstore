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
import za.co.tshim.domain.model.BookEntity;

/**
 *
 * @author F3340791
 */

//@EJB(name = "bookRepositoryBean", beanInterface = BookEntityRepositoryBeanLocal.class,beanName="bookBean")
@Stateless
public class BookEntityRepositoryBean implements BookEntityRepositoryBeanLocal,Serializable {

                                   
    @PersistenceContext(unitName="catalogue_0PU") 
    private EntityManager entityManager;

    @Override
    public void save(BookEntity book) {
        entityManager.persist(book);
    }

    @Override
    public void saveList(List<BookEntity> list) {
        for (BookEntity book : list) {
            save(book);
        }
    }

    @Override
    public List<BookEntity> findAll() {
      TypedQuery<BookEntity> query = entityManager.createQuery("from BookEntity", BookEntity.class);
      return query.getResultList();
    }

    @Override
    public void update(BookEntity book) {
        entityManager.merge(book);
        entityManager.flush();
    }

    @Override
    public void delete(BookEntity book) {
        BookEntity managedBook = entityManager.find(BookEntity.class, book.getId());
        entityManager.remove(managedBook);
    }
}
