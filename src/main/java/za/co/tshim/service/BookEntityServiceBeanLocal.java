/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.service;

import java.util.List;
import javax.ejb.Local;
import za.co.tshim.domain.model.BookEntity;

/**
 *
 * @author F3340791
 */
@Local
public interface BookEntityServiceBeanLocal {
    public void addBooks(List<BookEntity> books);
    
    public List<BookEntity> listAllBooks();

    public void updateBook(BookEntity book);

    public void delete(BookEntity book);
}
