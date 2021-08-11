package config;

public enum HttpCodes {
    SUCCESS200 (200,"OK"),
    SUCCESS201 (201, "Entity created"),
    SUCCESS204 (204, "Entity deleted"),
    ERROR400 (400, "Bad Request"),
    ERROR404 (404, "Not found"),
    ERROR409 (409,"Entity already exists");

    private int code;
    private String description;


    HttpCodes(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
