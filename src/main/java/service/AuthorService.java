package service;

import client.HttpClient;
import io.qameta.allure.Step;
import models.author.Author;
import org.testng.annotations.Listeners;
import response.BaseResponse;
import utils.CsvReader;
import utils.EndpointBuilder;

import java.util.LinkedList;

public class AuthorService extends BaseService{
    public static Author body;
    public static Integer authorId;

    @Step ("Create Author")
    public BaseResponse createAuthor (String entity, int id, String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(entity).get();
        body=generateCreateAuthorRequest(id,fileName);
        authorId=body.getAuthorId();
        rawResponse=HttpClient.post(endpoint,body);
        return rawResponse;
    }

    @Step ("Update Author")
    public BaseResponse updateAuthor (String entity, int id, int authorId, String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(entity).get();
        body=generateUpdateAuthorRequest(id,authorId,fileName);
        rawResponse=HttpClient.put(endpoint,body);
        return rawResponse;
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
