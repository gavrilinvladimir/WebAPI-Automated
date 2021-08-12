package service;

import client.HttpClient;
import models.author.Author;
import response.BaseResponse;
import utils.CsvReader;
import utils.EndpointBuilder;
import java.util.LinkedList;

public class AuthorService {
    public static Author body;
    public static Integer authorId;
    public BaseResponse createAuthor (String entity, int id, String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(entity).get();
        body=generateCreateAuthorRequest(id,fileName);
        authorId=body.getAuthorId();
        return HttpClient.post(endpoint,body);
    }

    public BaseResponse updateAuthor (String entity, int id, int authorId, String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(entity).get();
        body=generateUpdateAuthorRequest(id,authorId,fileName);
        return HttpClient.put(endpoint,body);
    }

    public Author generateCreateAuthorRequest(int id, String fileName) {
        LinkedList<Author> allTestAuthors = new CsvReader().getNewAuthorTestData(fileName);
        return allTestAuthors.get(id);
    }

    public Author generateUpdateAuthorRequest(int id, int authorId, String fileName) {
        LinkedList<Author> allTestAuthors = new CsvReader().getUpdateAuthorTestData(authorId,fileName);
        return allTestAuthors.get(id);
    }
}
