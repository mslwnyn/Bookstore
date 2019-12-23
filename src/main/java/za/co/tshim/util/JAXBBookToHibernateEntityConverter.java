package za.co.tshim.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import za.co.tshim.common.Exception.IncompleteBookDataException;
import za.co.tshim.common.jaxb.Book;
import za.co.tshim.domain.model.Author;
import za.co.tshim.domain.model.BookEntity;

/**
 *
 * @author troposene
 */
public class JAXBBookToHibernateEntityConverter {
     private static final Logger LOG = Logger.getLogger(JAXBBookToHibernateEntityConverter.class.getName());
     static  BookEntity  bookEntity ;
    private static BookEntity convertBookVoToEntity(Book book) throws IncompleteBookDataException {
          
         bookEntity = new BookEntity();
         if (book.getAuthors().size()==0){
                for (String name: book.getAuthors()){
                    Author author = new Author();
                    author.setAuthorName(name);
                    bookEntity.getAuthors().add(author);
                    author.setBook(bookEntity);
                }    
         }else{
            throw new IncompleteBookDataException("The book" + book.getTitle().getTitle()+" has no Author(s) provided"); 
         }  
        LOG.info(book.toString());
        
        bookEntity.getCategory().setCategoryName(book.getCategory());
        bookEntity.setPrice(book.getPrice());
        bookEntity.setYear(book.getYear());
        bookEntity.getWritten_lang().setLangCode(book.getTitle().getLanguage());
        if (!book.getTitle().getTitle().equalsIgnoreCase("")){
           bookEntity.setTitle(book.getTitle().getTitle());
        }else{
            throw new IncompleteBookDataException(book.toString()+" has no name provided");
        }

         return bookEntity; 
    }
   
    
    public static List<BookEntity> convertBooksToEntityList(List<Book> books) {
        List<BookEntity> list = new ArrayList<BookEntity>();
        for(Book book : books) {
            try
            {
                 list.add(convertBookVoToEntity(book));
                 
            }catch(IncompleteBookDataException ibe){
                ibe.printStackTrace();  
                //LOG.severe(ibe.getMessage());
            }     
        }
        return list;
    }
}
