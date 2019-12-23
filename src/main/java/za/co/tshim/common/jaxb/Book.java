/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.common.jaxb;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author F3340791
 */
@XmlRootElement(name="book")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "title", "year", "price", "authors" })
public class Book {
    
     @XmlAttribute
     private String category;
     @XmlElement(name = "title")
     private Title title;
     private int year;
     private double price;
     
     @XmlElementWrapper(name = "authors")
     @XmlElement(name = "author")
     private List<String> authors;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" + "category=" + category + ", title=" + title + ", year=" + year + ", price=" + price + ", authors=" + authors + '}';
    }

    
     
    
    
    
}
