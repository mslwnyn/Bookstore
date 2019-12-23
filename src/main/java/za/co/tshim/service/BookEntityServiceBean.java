/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import za.co.tshim.repository.BookEntityRepositoryBeanLocal;
import za.co.tshim.domain.model.BookEntity;
//import za.co.tshim.infrastructure.BookEntityRepositoryBeanLocal;
//import za.co.tshim.infrastructure.BookEntityRepositoryBeanLocal;

/**
 *
 * @author F3340791
 */
//@EJB(name = "bookServiceBean", beanInterface = BookEntityServiceBeanLocal.class,beanName="bookService")
@Stateless
public class BookEntityServiceBean implements BookEntityServiceBeanLocal,Serializable {
                     
//    //@EJB(beanName = "bookBean")
    @EJB//(lookup="java:global/bookstore/bookstore-infrastructure/bookBean")
    private BookEntityRepositoryBeanLocal bookBean;

    @Override
    public void addBooks(List<BookEntity> books) {
        bookBean.saveList(books);
    }

    @Override
    public List<BookEntity> listAllBooks() {
             return bookBean.findAll();
         
    }
    
    @Override
    public void updateBook(BookEntity book) {
        bookBean.update(book);
    }

    @Override
    public void delete(BookEntity book) {
        bookBean.delete(book);
    }
}
