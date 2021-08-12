package utils;

import com.opencsv.CSVReader;
import config.EnvConfig;
import models.author.Author;
import models.author.AuthorName;
import models.author.Birth;

import java.io.FileReader;
import java.util.LinkedList;

public class CsvReader  {
    String[] lineInArray;
    LinkedList<Author> testAuthors = new LinkedList<>();

    public LinkedList<Author>  getNewAuthorTestData (String fileName) {
        fileName = EnvConfig.CSVAUTHORFILEPATH + fileName;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            while ((lineInArray = reader.readNext()) != null) {
                Author author = new Author();
                AuthorName authorName = new AuthorName();
                Birth birth = new Birth();

                if (lineInArray[0].equals("null")) author.setAuthorId(null);
                else author.setAuthorId(Integer.parseInt(lineInArray[0]));

                if (lineInArray[1].equals("null")) authorName.setFirst("");
                else authorName.setFirst(lineInArray[1]);

                if (lineInArray[2].equals("null")) authorName.setSecond("");
                else authorName.setSecond(lineInArray[2]);
                author.setAuthorName(authorName);

                if (lineInArray[3].equals("null")) author.setNationality("");
                else author.setNationality(lineInArray[3]);

                if (lineInArray[4].equals("null")) birth.setDate("");
                else birth.setDate(lineInArray[4]);

                if (lineInArray[5].equals("null")) birth.setCountry("");
                else birth.setCountry(lineInArray[5]);

                if (lineInArray[6].equals("null")) birth.setCity("");
                else birth.setCity(lineInArray[6]);
                author.setBirth(birth);
                if (lineInArray[7].equals("null")) author.setAuthorDescription("");
                else author.setAuthorDescription(lineInArray[7]);
                testAuthors.add(author);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return testAuthors;
    }

    public LinkedList<Author>  getUpdateAuthorTestData (int id, String fileName) {
        fileName = EnvConfig.CSVAUTHORFILEPATH + fileName;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            while ((lineInArray = reader.readNext()) != null) {
                Author author = new Author();
                AuthorName authorName = new AuthorName();
                Birth birth = new Birth();

                author.setAuthorId(id);

                if (lineInArray[1].equals("null")) authorName.setFirst("");
                else authorName.setFirst(lineInArray[1]);

                if (lineInArray[2].equals("null")) authorName.setSecond("");
                else authorName.setSecond(lineInArray[2]);
                author.setAuthorName(authorName);

                if (lineInArray[3].equals("null")) author.setNationality("");
                else author.setNationality(lineInArray[3]);

                if (lineInArray[4].equals("null")) birth.setDate("");
                else birth.setDate(lineInArray[4]);

                if (lineInArray[5].equals("null")) birth.setCountry("");
                else birth.setCountry(lineInArray[5]);

                if (lineInArray[6].equals("null")) birth.setCity("");
                else birth.setCity(lineInArray[6]);
                author.setBirth(birth);
                if (lineInArray[7].equals("null")) author.setAuthorDescription("");
                else author.setAuthorDescription(lineInArray[7]);
                testAuthors.add(author);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return testAuthors;
    }
}
