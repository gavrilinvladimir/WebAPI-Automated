package service;

import client.HttpClient;
import models.book.Book;
import org.testng.annotations.Listeners;
import response.BaseResponse;
import utils.CsvReader;
import utils.EndpointBuilder;

import java.util.LinkedList;

public class BookService extends BaseService {
    public static Book body;
    public static Integer bookId;
    public BaseResponse createBook (String entity, int id, int authorId,int genreId, String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(entity).addEntityId(authorId).addEntityId(genreId).get();
        body=generateCreateBookRequest(id,fileName);
        bookId=body.getBookId();
        return HttpClient.post(endpoint,body);
    }

    public BaseResponse updateBook (String entity, int id, int authorId, String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(entity).get();
        body=generateUpdateBookRequest(id,authorId,fileName);
        return HttpClient.put(endpoint,body);
    }


    public Book generateCreateBookRequest(int id, String fileName) {
        LinkedList<Book> allTestAuthors = new CsvReader().getNewBookTestData(fileName);
        return allTestAuthors.get(id);
    }

    public Book generateUpdateBookRequest(int id, int authorId, String fileName) {
        LinkedList<Book> allTestAuthors = new CsvReader().getUpdateBookTestData(authorId,fileName);
        return allTestAuthors.get(id);
    }
}
