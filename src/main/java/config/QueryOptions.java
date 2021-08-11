package config;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import utils.JsonSamplesReader;



import java.io.IOException;
import java.util.HashMap;

public class QueryOptions  {
    public HashMap getQueryOptions (String fileName) {
        JSONObject root = new JsonSamplesReader(fileName).get();
        HashMap <String, Object> queryOptions = new HashMap<String, Object>();
        try
        {
            queryOptions = new ObjectMapper().readValue(root.toString(),HashMap.class);
        }
        catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queryOptions;
    }
}
