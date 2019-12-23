/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import za.co.tshim.repository.CategoryEntityRepositoryBeanLocal;
import za.co.tshim.domain.model.Category;

/**
 *
 * @author F3340791
 */

//@EJB(name = "categoryService", beanInterface = CategoryServiceBeanLocal.class,beanName="categoryBean")
@Stateless
public class CategoryServiceBean implements CategoryServiceBeanLocal,Serializable{

    //private static final Logger LOG = Logger.getLogger(CategoryServiceBean.class.getName());
       
    @EJB
    private CategoryEntityRepositoryBeanLocal categoryService;

    @Override
    public List<Category> findAll() {
              return categoryService.findAll();
    }
}
