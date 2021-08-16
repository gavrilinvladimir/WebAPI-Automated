package config;

import utils.PropertiesReader;

public class EnvConfig {
    public static final String HOST = EnvConfig.getHost();
    public static final String LIB = EnvConfig.getLib();
    public static final String JSONFILEPATH = EnvConfig.getJsonFilePath();
    public static final String CSVAUTHORFILEPATH = EnvConfig.getCsvAuthorFilePath();
    public static final String CSVBOOKFILEPATH = EnvConfig.getCsvBookFilePath();
    public static final String CSVGENREFILEPATH = EnvConfig.getCsvGenreFilePath();




    private static String getHost() {
        return new PropertiesReader("src/main/resources/", "config.properties").get("host");
    }

    private static String getLib() {
        return new PropertiesReader("src/main/resources/", "config.properties").get("lib");
    }

    private static String getJsonFilePath() {
        return new PropertiesReader("src/main/resources/", "config.properties").get("json.file.path");
    }

    private static String getCsvAuthorFilePath() {
        return new PropertiesReader("src/main/resources/", "config.properties").get("csv.author.test");
    }

    private static String getCsvBookFilePath() {
        return new PropertiesReader("src/main/resources/", "config.properties").get("csv.book.test");
    }

    private static String getCsvGenreFilePath() {
        return new PropertiesReader("src/main/resources/", "config.properties").get("csv.genre.test");
    }
}
