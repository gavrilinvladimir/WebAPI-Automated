package utils;

import config.EnvConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonSamplesReader {
    private JSONObject json;

    public JsonSamplesReader(String fileName) {
        String path = EnvConfig.JSONFILEPATH + fileName;
        try (FileReader input = new FileReader(path)) {
            Object obj = new JSONParser().parse(input);
            json = (JSONObject) obj;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject get() {
        return this.json;
    }
}
