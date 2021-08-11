package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import config.EnvConfig;
import models.author.Author;
import models.author.AuthorName;
import models.author.Birth;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CsvReader  {
    String[] lineInArray;
    LinkedList<Author> testAuthors = new LinkedList<Author>();
    public LinkedList getAuthorTestData (String fileName) {
        fileName = EnvConfig.CSVAUTHORFILEPATH + fileName;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            while ((lineInArray = reader.readNext()) != null) {
                Author author = new Author();
                AuthorName authorName = new AuthorName();
                Birth birth = new Birth();

                author.setAuthorId(Integer.parseInt(lineInArray[0]));

                authorName.setFirst(lineInArray[1]);
                authorName.setSecond(lineInArray[2]);
                author.setAuthorName(authorName);

                author.setNationality(lineInArray[3]);

                birth.setDate(lineInArray[4]);
                birth.setCountry(lineInArray[5]);
                birth.setCity(lineInArray[6]);
                author.setBirth(birth);

                author.setAuthorDescription(lineInArray[7]);
                testAuthors.add(author);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return testAuthors;
    }
}
