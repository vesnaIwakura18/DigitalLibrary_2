package kz.bisen.springwebapp1.project2Boot.soap.client.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(
        serviceName = "BookService"
)
public class BookServiceImpl {
    @WebMethod(operationName = "request-book")
    public List<String> request(List<String> isbns) {


        return isbns;
    }
}
