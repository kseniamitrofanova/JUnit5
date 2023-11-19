package data;

public enum Language {
    SELENIDE("SELENIDE"),
    JUNIT("JUNIT");
    public final String description;

    Language(String description){
        this.description = description;
    }
}
