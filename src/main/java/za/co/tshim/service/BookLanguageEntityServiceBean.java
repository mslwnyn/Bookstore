/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import za.co.tshim.domain.model.BookLanguage;
import za.co.tshim.repository.BookLanguageEntityRepositoryBeanLocal;

/**
 *
 * @author F3340791
 */
@Stateless
public class BookLanguageEntityServiceBean implements BookLanguageEntityServiceBeanLocal {

    //private static final Logger LOG = Logger.getLogger(CategoryServiceBean.class.getName());
       
    @EJB
    private BookLanguageEntityRepositoryBeanLocal categoryService;

    @Override
    public List<BookLanguage> findAll() {
              return categoryService.findAll();
    }
}
