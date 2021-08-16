package models.book;

public class Size {
    public Integer height;
    public Integer width;
    public Integer length;

    public Integer getHeight() {
        return height;
    }

    public Size setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public Size setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public Size setLength(Integer length) {
        this.length = length;
        return this;
    }
}
