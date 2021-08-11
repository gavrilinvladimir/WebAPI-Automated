package models.author;

import models.BaseModel;

public class Author extends BaseModel {
    public Integer authorId;
    public AuthorName authorName;
    public String nationality;
    public Birth birth;
    public String authorDescription;

    public Integer getAuthorId() {
        return authorId;
    }

    public Author setAuthorId(Integer authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public Author setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public AuthorName getAuthorName() {
        return authorName;
    }

    public Author setAuthorName(AuthorName authorName) {
        this.authorName = authorName;
        return this;
    }

    public Birth getBirth() {
        return birth;
    }

    public Author setBirth(Birth birth) {
        this.birth = birth;
        return this;
    }

    public String getAuthorDescription() {
        return authorDescription;
    }

    public Author setAuthorDescription(String authorDescription) {
        this.authorDescription = authorDescription;
        return this;
    }
}
