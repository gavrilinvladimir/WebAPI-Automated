package models.book;

import models.BaseModel;

public class Book extends BaseModel {
    public Integer bookId;
    public String bookName;
    public String bookLanguage;
    public String bookDescription;
    public Additional additional;
    public Integer publicationYear;


    public Integer getBookId() {
        return bookId;
    }

    public Book setBookId(Integer bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public Book setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
        return this;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public Book setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
        return this;
    }

    public Additional getAdditional() {
        return additional;
    }

    public Book setAdditional(Additional additional) {
        this.additional = additional;
        return this;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public Book setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
        return this;
    }
}
