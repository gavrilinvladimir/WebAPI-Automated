package utils;

import com.opencsv.CSVReader;
import config.EnvConfig;
import io.qameta.allure.Step;
import models.author.Author;
import models.author.AuthorName;
import models.author.Birth;
import models.book.Additional;
import models.book.Book;
import models.book.Size;
import models.genre.Genre;

import java.io.FileReader;
import java.util.LinkedList;

public class CsvReader  {
    String[] lineInArray;
    LinkedList<Author> testAuthors = new LinkedList<>();
    LinkedList<Book> testBooks = new LinkedList<>();
    LinkedList<Genre> testGenres = new LinkedList<>();

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

    public LinkedList<Book>  getNewBookTestData (String fileName) {
        fileName = EnvConfig.CSVBOOKFILEPATH + fileName;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            while ((lineInArray = reader.readNext()) != null) {
                Book book = new Book();
                Additional additional = new Additional();
                Size size= new Size();

                if (lineInArray[0].equals("null")) book.setBookId(null);
                else book.setBookId(Integer.parseInt(lineInArray[0]));
                if (lineInArray[1].equals("null")) book.setBookName("");
                else book.setBookName(lineInArray[1]);
                if (lineInArray[2].equals("null")) book.setBookLanguage("");
                else book.setBookLanguage(lineInArray[2]);
                if (lineInArray[3].equals("null")) book.setBookDescription("");
                else book.setBookDescription(lineInArray[3]);

                if (lineInArray[4].equals("null")) additional.setPageCount(null);
                else additional.setPageCount(Integer.parseInt(lineInArray[4]));
                if (lineInArray[5].equals("null")) size.setHeight(null);
                else size.setHeight(Integer.parseInt(lineInArray[5]));
                if (lineInArray[6].equals("null")) size.setWidth(null);
                else size.setWidth(Integer.parseInt(lineInArray[6]));
                if (lineInArray[7].equals("null")) size.setLength(null);
                else size.setLength(Integer.parseInt(lineInArray[7]));
                additional.setSize(size);
                book.setAdditional(additional);

                if (lineInArray[8].equals("null")) book.setPublicationYear(null);
                else book.setPublicationYear(Integer.parseInt(lineInArray[8]));
                testBooks.add(book);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return testBooks;
    }

    public LinkedList<Book>  getUpdateBookTestData (int id, String fileName) {
        fileName = EnvConfig.CSVBOOKFILEPATH + fileName;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            while ((lineInArray = reader.readNext()) != null) {
                Book book = new Book();
                Additional additional = new Additional();
                Size size= new Size();
                book.setBookId(id);

                if (lineInArray[1].equals("null")) book.setBookName("");
                else book.setBookName(lineInArray[1]);
                if (lineInArray[2].equals("null")) book.setBookLanguage("");
                else book.setBookLanguage(lineInArray[2]);
                if (lineInArray[3].equals("null")) book.setBookDescription("");
                else book.setBookDescription(lineInArray[3]);

                if (lineInArray[4].equals("null")) additional.setPageCount(null);
                else additional.setPageCount(Integer.parseInt(lineInArray[4]));
                if (lineInArray[5].equals("null")) size.setHeight(null);
                else size.setHeight(Integer.parseInt(lineInArray[5]));
                if (lineInArray[6].equals("null")) size.setWidth(null);
                else size.setWidth(Integer.parseInt(lineInArray[6]));
                if (lineInArray[7].equals("null")) size.setLength(null);
                else size.setLength(Integer.parseInt(lineInArray[7]));
                additional.setSize(size);
                book.setAdditional(additional);

                if (lineInArray[8].equals("null")) book.setPublicationYear(null);
                else book.setPublicationYear(Integer.parseInt(lineInArray[8]));
                testBooks.add(book);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return testBooks;
    }

    public LinkedList<Genre>  getNewGenreTestData (String fileName) {
        fileName = EnvConfig.CSVGENREFILEPATH + fileName;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            while ((lineInArray = reader.readNext()) != null) {
                Genre genre = new Genre();
                if (lineInArray[0].equals("null")) genre.setGenreId(null);
                else genre.setGenreId(Integer.parseInt(lineInArray[0]));
                if (lineInArray[1].equals("null")) genre.setGenreName("");
                else genre.setGenreName(lineInArray[1]);
                if (lineInArray[2].equals("null")) genre.setGenreDescription("");
                else genre.setGenreDescription(lineInArray[2]);
                testGenres.add(genre);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return testGenres;
    }


    public LinkedList<Genre>  getUpdateGenreTestData (int id, String fileName) {
        fileName = EnvConfig.CSVGENREFILEPATH + fileName;
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            while ((lineInArray = reader.readNext()) != null) {
                Genre genre = new Genre();
                genre.setGenreId(id);

                if (lineInArray[1].equals("null")) genre.setGenreName("");
                else genre.setGenreName(lineInArray[1]);
                if (lineInArray[2].equals("null")) genre.setGenreDescription("");
                else genre.setGenreDescription(lineInArray[2]);
                testGenres.add(genre);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return testGenres;
    }
}
