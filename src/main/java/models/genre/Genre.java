package models.genre;


import models.BaseModel;

public class Genre extends BaseModel {
    public Integer genreId;
    public String genreName;
    public String genreDescription;

    public Integer getGenreId() {
        return genreId;
    }

    public Genre setGenreId(Integer genreId) {
        this.genreId = genreId;
        return this;
    }

    public String getGenreName() {
        return genreName;
    }

    public Genre setGenreName(String genreName) {
        this.genreName = genreName;
        return this;
    }

    public String getGenreDescription() {
        return genreDescription;
    }

    public Genre setGenreDescription(String genreDescription) {
        this.genreDescription = genreDescription;
        return this;
    }
}
