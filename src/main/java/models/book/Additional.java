package models.book;

public class Additional {
    public Integer pageCount;
    public Size size;

    public Integer getPageCount() {
        return pageCount;
    }

    public Additional setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public Size getSize() {
        return size;
    }

    public Additional setSize(Size size) {
        this.size = size;
        return this;
    }
}
