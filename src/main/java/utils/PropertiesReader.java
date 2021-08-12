package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private Properties props;

    public PropertiesReader(String filePath, String fileName) {
        String path = filePath + fileName;
        try (InputStream input = new FileInputStream(path)) {
            this.props = new Properties();
            this.props.load(input);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String property) {
        return this.props.getProperty(property);
    }
}
