/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author F3340791
 */
@Entity
@Table(name = "BOOK_IMAGE")
public class BookImage implements Serializable{
    
     @Id
    @GeneratedValue
    @Column(name = "IMAGE_ID")
    private int id;
       
    @Column(name = "DATA", unique = false, nullable = false, length = 100000)
    @Lob
    private byte[] data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookImage{" + "id=" + id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        return hash;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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
        final BookImage other = (BookImage) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

      
    
}
