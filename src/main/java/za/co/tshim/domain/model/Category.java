/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author F3340791
 */
@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {
    
     @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private int id;
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List <BookEntity> book  = new ArrayList<BookEntity>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", categoryName=" + categoryName + '}';
    }

    public List<BookEntity> getBook() {
        return book;
    }

    public void setBook(List<BookEntity> book) {
        this.book = book;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.categoryName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.categoryName, other.categoryName)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
