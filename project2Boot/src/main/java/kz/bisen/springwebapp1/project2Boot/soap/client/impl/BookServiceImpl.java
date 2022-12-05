package kz.bisen.springwebapp1.project2Boot.soap.client.impl;

import kz.bisen.springwebapp1.project2Boot.soap.client.BookService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(
        serviceName = "BookService",
        portName = "BookPort",
        targetNamespace = "http://service.ws.sample/",
        endpointInterface = "kz.bisen.springwebapp1.project2Boot.soap.client.BookService"
)
public class BookServiceImpl implements BookService {
    @WebMethod(operationName = "do-request")
    public List<String> doRequest(List<String> isbns) {


        return isbns;
    }
}
