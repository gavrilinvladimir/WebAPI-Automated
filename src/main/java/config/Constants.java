package config;

import org.apache.log4j.Logger;

public class Constants {
    public static int bookRow = 0;
    public static int bookUpdateRow = 1;
    public static int genreRow = 0;
    public static int genreUpdateRow = 1;
    public static int authorRow = 0;
    public static int authorUpdateRow = 1;

    public static String genre =ServicesConfig.GENRE.getSingular();
    public static String genres =ServicesConfig.GENRE.getPlural();
    public static String book =ServicesConfig.BOOK.getSingular();
    public static String books =ServicesConfig.BOOK.getPlural();
    public static String author =ServicesConfig.AUTHOR.getSingular();
    public static String authors =ServicesConfig.AUTHOR.getPlural();
    public static String search =ServicesConfig.SEARCH.getSingular();

    public static String authorPositive ="authorPositive.csv";
    public static String authorNegative ="authorNegative.csv";
    public static String bookPositive ="bookPositive.csv";
    public static String bookNegative ="bookNegative.csv";
    public static String genrePositive ="genrePositive.csv";
    public static String genreNegative ="genreNegative.csv";

    public static String forcibly ="true";
    public static String queryAuthor ="testData/queryOptionsAuthor.json";
    public static String queryBook ="testData/queryOptionsBook.json";
    public static String queryGenre ="testData/queryOptionsGenre.json";
    public static String searchAuthor ="testData/searchAuthor.json";
    public static String searchBook ="testData/searchBook.json";
    public static String searchGenre ="testData/searchGenre.json";

}
