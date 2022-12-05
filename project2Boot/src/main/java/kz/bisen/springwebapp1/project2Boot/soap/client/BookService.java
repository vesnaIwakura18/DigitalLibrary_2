package kz.bisen.springwebapp1.project2Boot.soap.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@WebService(targetNamespace = "http://service.ws.sample/", name = "BookService")
public interface BookService {

    @WebResult(name = "result", targetNamespace = "")
    @RequestWrapper(
            localName = "book-request",
            targetNamespace = "http://service.ws.sample/",
            className = "sample.ws.service.BookRequestService")
    @WebMethod(action = "urn:DoRequest")
    @ResponseWrapper(
            localName = "book-response",
            targetNamespace = "http://service.ws.sample/",
            className = "sample.ws.service.BookResponseService")
    List<String> doRequest(@WebParam(name = "isbns", targetNamespace = "") List<String> isbns);
}
