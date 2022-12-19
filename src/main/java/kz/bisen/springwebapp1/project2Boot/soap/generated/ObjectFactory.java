
package kz.bisen.springwebapp1.project2Boot.soap.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kz.bisen.springcourse.springpublishingwebapp.soap.impl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetBooks_QNAME = new QName("http://impl.soap.springpublishingwebapp.springcourse.bisen.kz/", "get-books");
    private final static QName _GetBooksResponse_QNAME = new QName("http://impl.soap.springpublishingwebapp.springcourse.bisen.kz/", "get-booksResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kz.bisen.springcourse.springpublishingwebapp.soap.impl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBooks }
     * 
     */
    public GetBooks createGetBooks() {
        return new GetBooks();
    }

    /**
     * Create an instance of {@link GetBooksResponse }
     * 
     */
    public GetBooksResponse createGetBooksResponse() {
        return new GetBooksResponse();
    }

    /**
     * Create an instance of {@link SoapBook }
     * 
     */
    public SoapBook createSoapBook() {
        return new SoapBook();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBooks }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetBooks }{@code >}
     */
    @XmlElementDecl(namespace = "http://impl.soap.springpublishingwebapp.springcourse.bisen.kz/", name = "get-books")
    public JAXBElement<GetBooks> createGetBooks(GetBooks value) {
        return new JAXBElement<GetBooks>(_GetBooks_QNAME, GetBooks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBooksResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetBooksResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://impl.soap.springpublishingwebapp.springcourse.bisen.kz/", name = "get-booksResponse")
    public JAXBElement<GetBooksResponse> createGetBooksResponse(GetBooksResponse value) {
        return new JAXBElement<GetBooksResponse>(_GetBooksResponse_QNAME, GetBooksResponse.class, null, value);
    }

}
