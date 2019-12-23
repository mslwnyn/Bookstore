package za.co.tshim.common.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import za.co.tshim.common.jaxb.Book;
import za.co.tshim.common.jaxb.BookStore;

/**
 *
 * @author troposene
 */
public class XmlHelper {

    public static List<Book> convertXmlToBookList(String xml) {
        try {
            InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            JAXBContext jaxbContext = JAXBContext.newInstance(BookStore.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            BookStore bookStore = (BookStore) jaxbUnmarshaller.unmarshal(stream);
            return bookStore.getBookList();
        } catch (JAXBException ex) {
            Logger.getLogger(XmlHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
