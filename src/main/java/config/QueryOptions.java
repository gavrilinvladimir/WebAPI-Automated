package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import utils.JsonSamplesReader;



import java.io.IOException;
import java.util.HashMap;

public class QueryOptions  {
    public HashMap <String, Object> getQueryOptions (String fileName) {
        JSONObject root = new JsonSamplesReader(fileName).get();
        HashMap <String, Object> queryOptions = new HashMap<>();
        try
        {
            queryOptions = new ObjectMapper().readValue(root.toString(),HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queryOptions;
    }
}
