/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.domain.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author F3340791
 */
@Entity
@Table(name = "AUTHOR")
//@DynamicUpdate(true)
//@org.hibernate.annotations.Entity(
//        dynamicUpdate = true
//)
public class Author implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "AUTHOR_ID")
    private int id;
    @Column(name = "AUTHOR_NAME")
    private String authorName;
    
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private BookEntity book;


    public int getId() {
        return id;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
    
    

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", authorName=" + authorName + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.authorName);
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
        final Author other = (Author) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.authorName, other.authorName)) {
            return false;
        }
        return true;
    }
   
    
    
    
}
