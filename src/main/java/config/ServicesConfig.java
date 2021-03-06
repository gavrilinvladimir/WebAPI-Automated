package config;

public enum ServicesConfig {
    AUTHOR ("author","authors"),
    BOOK ("book","books"),
    GENRE ("genre","genres"),
    SEARCH ("search", "none");

    private final String singular;
    private final String plural;


    ServicesConfig(String singular, String plural) {
        this.singular = singular;
        this.plural = plural;
    }

    public String getSingular() {
        return singular;
    }

    public String getPlural() {
        return plural;
    }
}
