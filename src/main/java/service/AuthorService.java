package service;

import client.HttpClient;
import models.BaseModel;
import models.author.Author;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import response.BaseResponse;
import utils.CsvReader;
import utils.EndpointBuilder;
import utils.JsonSamplesReader;

import java.util.LinkedList;
import java.util.List;

public class AuthorService {
    private Logger LOG = Logger.getLogger(AuthorService.class);
    public static Author body;
    public static Integer authorId;
    public BaseResponse createAuthor (String entity, int id, String fileName) {
        String endpoint = new EndpointBuilder().addEntityType(entity).get();
        body=generateAuthorRequest(id,fileName);
        authorId=body.getAuthorId();
        return HttpClient.post(endpoint,body);
    }
/*
    public BaseResponse updateAuthor (String entity, int entityId, String fileName, String updateParam, String updateValue) {
        String endpoint = new EndpointBuilder().addEntityType(entity).get();
        LOG.info(String.format("Endpoint is: %s",endpoint));
        JSONObject source = updateJsonSample(entityId,fileName);
        JSONObject authorName= (JSONObject) source.get("authorName");
        JSONObject birth= (JSONObject) source.get("birth");
        if (source.containsKey(updateParam)) {
            source.put(updateParam,updateValue);
        }
        else if (authorName.containsKey(updateParam)) {
            authorName.put(updateParam,updateValue);
        }
        else {
            birth.put(updateParam,updateValue);
        }

        String body=source.toString();
        return HttpClient.put(endpoint,body);
    }
*/
    public JSONObject updateJsonSample (int entityId, String fileName) {
        JSONObject root = new JsonSamplesReader (fileName).get();
        root.put("authorId",entityId);

        JSONObject authorName= (JSONObject) root.get("authorName");
        if (authorName!= null) {
            authorName.put("first","testName");
            authorName.put("second","testSurname");
            root.put("authorName", authorName);
        }
        root.put("nationality","Ukrainian");

        JSONObject birth= (JSONObject) root.get("birth");
        birth.put("country","Ukraine");
        birth.put("city","Kyiv");
        root.put("birth",birth);
        root.put("authorDescription","test");
        return root;
    }



    // TODO properly handle genre entity
    public Author generateAuthorRequest(int id, String fileName) {
        LinkedList<Author> allTestAuthors = new CsvReader().getAuthorTestData(fileName);
        Author body = allTestAuthors.get(id);
        return body;
    }
}
