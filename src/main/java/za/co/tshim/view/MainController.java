package za.co.tshim.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import za.co.tshim.service.BookEntityServiceBeanLocal;
import za.co.tshim.common.jaxb.Book;
import za.co.tshim.common.util.XmlHelper;
import za.co.tshim.util.JAXBBookToHibernateEntityConverter;

@ManagedBean
@SessionScoped
public class MainController implements Serializable {
    
    
    

    private static final long serialVersionUID = 20160906L;
    private static final Logger logger = Logger.getLogger(MainController.class.getSimpleName());

    private String xmlText;
    private Part file;
    
    @EJB//(name = "bookService")
    private BookEntityServiceBeanLocal bookService;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getXmlText() {
        return xmlText;
    }

    public void setXmlText(String xmlText) {
        this.xmlText = xmlText;
    }

    public void upload() {
        try {
            if (file != null) {
                xmlText = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
          //  xmlText =  BookStoreFileReader.readXmlFile((File) file);
                logger.log(Level.INFO, "Content: \n\n{0}", xmlText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String home() {
        return "start?faces-redirect=true";
    }

    public String saveContent() {
        List<Book> bookList = XmlHelper.convertXmlToBookList(xmlText);
        for (Book book : bookList){
            logger.info(book.toString());
        }
        bookService.addBooks(JAXBBookToHibernateEntityConverter.convertBooksToEntityList(bookList));

        return "index?faces-redirect=true";
    }
}
