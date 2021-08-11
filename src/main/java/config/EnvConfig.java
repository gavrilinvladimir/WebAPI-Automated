package config;

import utils.PropertiesReader;

public class EnvConfig {
    public static final String HOST = EnvConfig.getHost();
    public static final String LIB = EnvConfig.getLib();
    public static final String JSONFILEPATH = EnvConfig.getJsonFilePath();


    private static String getHost() {
        return new PropertiesReader("src/main/resources/", "config.properties").get("host");
    }

    private static String getLib() {
        return new PropertiesReader("src/main/resources/", "config.properties").get("lib");
    }

    private static String getJsonFilePath() {
        return new PropertiesReader("src/main/resources/", "config.properties").get("json.file.path");
    }
}
