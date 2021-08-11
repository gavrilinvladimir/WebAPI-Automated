package models.author;

public class AuthorName{
    public String first;
    public String second;

    public String getFirst() {
        return first;
    }

    public AuthorName setFirst(String first) {
        this.first = first;
        return this;
    }

    public String getSecond() {
        return second;
    }

    public AuthorName setSecond(String second) {
        this.second = second;
        return this;
    }
}
