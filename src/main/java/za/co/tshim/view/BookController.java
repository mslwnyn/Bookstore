package za.co.tshim.view;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import za.co.tshim.domain.model.Author;
import za.co.tshim.service.BookEntityServiceBeanLocal;
import za.co.tshim.domain.model.BookEntity;
import za.co.tshim.domain.model.BookLanguage;
import za.co.tshim.domain.model.Category;
import za.co.tshim.service.BookLanguageEntityServiceBeanLocal;
import za.co.tshim.service.CategoryServiceBeanLocal;

/**
 *
 * @author troposene
 */
@ManagedBean
@SessionScoped
public class BookController implements Serializable{
    static Logger log = Logger.getLogger(BookController.class.getName());
    private List<BookEntity> bookList;
    
    private  List<SelectItem> categoriesSelectItems;
    Map <Integer,String> categoryMap= new HashMap<Integer,String>() {};
    
    private  List<SelectItem> langauageSelectItems;
    Map <String,String> languageMap= new HashMap<String,String>() {};
    
    private BookEntity selectedBook;
    
    private StreamedContent bookImage; 
    
    @EJB
    private BookEntityServiceBeanLocal bookService;
    
    @EJB
    private CategoryServiceBeanLocal categoryService;
    
     @EJB 
    private BookLanguageEntityServiceBeanLocal bookLanguageService;
    
     
     
    @PostConstruct
    public void init() {
       bookList = bookService.listAllBooks();  
       
       for (BookEntity book:bookList){
           
           for (Author au: book.getAuthors()){
               log.info("*******" +au.getAuthorName()  +"******");
             //  System.out.println("*******************************************" +au.getAuthorName()  +"*************************************************************");
           }
       }
       categoriesSelectItems = getAllCategories(categoryService.findAll());
       langauageSelectItems= getAllLanguages(bookLanguageService.findAll());
    }

    public BookEntity getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(BookEntity selectedBook) {
        this.selectedBook = selectedBook;
    }

    public BookEntityServiceBeanLocal getBookService() {
        return bookService;
    }

    public void setBookService(BookEntityServiceBeanLocal bookService) {
        this.bookService = bookService;
    }

    public List<SelectItem> getCategoriesSelectItems() {
        return categoriesSelectItems;
    }

    public void setCategoriesSelectItems(List<SelectItem> categoriesSelectItems) {
        this.categoriesSelectItems = categoriesSelectItems;
    }   

    public Map<Integer, String> getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(Map<Integer, String> categoryMap) {
        this.categoryMap = categoryMap;
    }
    
    
    public CategoryServiceBeanLocal getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryServiceBeanLocal categoryService) {
        this.categoryService = categoryService;
    }

    public List<SelectItem> getLangauageSelectItems() {
        return langauageSelectItems;
    }

    public void setLangauageSelectItems(List<SelectItem> langauageSelectItems) {
        this.langauageSelectItems = langauageSelectItems;
    }

    public BookLanguageEntityServiceBeanLocal getBookLanguageService() {
        return bookLanguageService;
    }

    public void setBookLanguageService(BookLanguageEntityServiceBeanLocal bookLanguageService) {
        this.bookLanguageService = bookLanguageService;
    }
    
        
    
    public List<BookEntity> getBookList() {
        if(bookList==null){
            bookList = bookService.listAllBooks();
        }
        return bookList;  
    }

    public void setBookList(List<BookEntity> bookList) {
        this.bookList = bookList;
    }
    
    public void delete(BookEntity book) {
        bookService.delete(book);
        bookList = bookService.listAllBooks();
    }

    public void onRowEdit(RowEditEvent event) { 
        log.info("******Editing a Row *******");
        BookEntity book = (BookEntity) event.getObject();
        book.getCategory().setCategoryName((String)categoryMap.get(book.getCategory().getId()));
        book.getWritten_lang().setLanguageName(languageMap.get((String)book.getWritten_lang().getLangCode()));
        bookService.updateBook(book);
        FacesMessage msg = new FacesMessage("Book Edited", (book).getTitle() );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled",
                ((BookEntity) event.getObject()).getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        DataTable o = (DataTable) event.getSource();
        BookEntity book = (BookEntity) o.getRowData();
        bookService.updateBook(book);
        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = (UploadedFile) event.getFile();
        selectedBook.getBookImage().setData(file.getContents());
        bookService.updateBook(selectedBook);        
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void setBookImage(StreamedContent bookImage) {
		this.bookImage = bookImage;
    }
    
    public StreamedContent getBookImage() throws IOException, SQLException {
 
		FacesContext context = FacesContext.getCurrentInstance();
 
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}else {
 	                return new DefaultStreamedContent(new ByteArrayInputStream(selectedBook.getBookImage().getData()));
 		}
    }
    
    public List<SelectItem> getAllCategories(List<Category> categories){
        
        List <SelectItem> items = new ArrayList <SelectItem>();
             
        for  (Category category :categories){
            items.add(new SelectItem(category.getId(),category.getCategoryName()));
             categoryMap.put(category.getId(), category.getCategoryName());
        }
        
        return items;
    }
    public List<SelectItem> getAllLanguages(List<BookLanguage> bookLanguage){
        
        List <SelectItem> items = new ArrayList <SelectItem>();
             
        for  (BookLanguage language :bookLanguage){
            items.add(new SelectItem(language.getLangCode(),language.getLanguageName()));
             languageMap.put(language.getLangCode(), language.getLanguageName());
        }
        
        return items;
    }
    
}
