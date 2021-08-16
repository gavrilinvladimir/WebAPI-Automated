package service;

import client.HttpClient;
import models.author.Author;
import models.genre.Genre;
import org.testng.annotations.Listeners;
import response.BaseResponse;
import utils.CsvReader;
import utils.EndpointBuilder;


import java.util.LinkedList;

public class GenreService extends BaseService {
    public static Genre body;
    public static Integer genreId;
    public BaseResponse createGenre (String entity, int id, String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(entity).get();
        body=generateCreateGenreRequest(id,fileName);
        genreId=body.getGenreId();
        return HttpClient.post(endpoint,body);
    }

    public BaseResponse updateGenre (String entity, int id, int authorId, String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(entity).get();
        body=generateUpdateGenreRequest(id,authorId,fileName);
        return HttpClient.put(endpoint,body);
    }

    public Genre generateCreateGenreRequest(int id, String fileName) {
        LinkedList<Genre> allTestGenres = new CsvReader().getNewGenreTestData(fileName);
        return allTestGenres.get(id);
    }

    public Genre generateUpdateGenreRequest(int id, int genreId, String fileName) {
        LinkedList<Genre> allTestGenres = new CsvReader().getUpdateGenreTestData(genreId,fileName);
        return allTestGenres.get(id);
    }
}
