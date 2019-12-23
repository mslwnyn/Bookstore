/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.repository;

import java.util.List;
import javax.ejb.Local;
import za.co.tshim.domain.model.BookEntity;

/**
 *
 * @author F3340791
 */
@Local
public interface BookEntityRepositoryBeanLocal {
     void save(BookEntity t);
    
    void saveList(List<BookEntity> list);

    public List<BookEntity> findAll();

    public void update(BookEntity book);

    public void delete(BookEntity book);
}
