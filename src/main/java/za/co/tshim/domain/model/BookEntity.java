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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import org.hibernate.annotations.Cascade;

/**
 *
 * @author troposene
 */
@Entity
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID")
    private int id;    
    
    @Column(name = "BOOK_TITLE")
    private String title;
    
    
    @OneToOne(cascade = {CascadeType.ALL})
  //  @Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private BookLanguage written_lang = new BookLanguage(); 
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy="book",fetch =FetchType.EAGER ,)
    private  List<Author> authors = new ArrayList<Author>(); 
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")    
    private Category category= new Category();    
    
    
    private int year;
    private double price;
    
    @OneToOne(cascade = CascadeType.ALL)
    private BookImage bookImage = new BookImage();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BookLanguage getWritten_lang() {
        return written_lang;
    }

    public void setWritten_lang(BookLanguage written_lang) {
        this.written_lang = written_lang;
    }

      

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BookImage getBookImage() {
        return bookImage;
    }

    public void setBookImage(BookImage bookImage) {
        this.bookImage = bookImage;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

   
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.title);
        hash = 67 * hash + Objects.hashCode(this.category);
        hash = 67 * hash + this.year;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
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
        final BookEntity other = (BookEntity) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }

  
    

   

   

  
    
    
}