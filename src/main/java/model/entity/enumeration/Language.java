package model.entity.enumeration;

public enum  Language {
    ENGLISH("en"),
    RUSSIAN("ru");

    private String locale;

    Language(String locale){
        this.locale = locale;
    }

    Language(){}

    public String getLocale() {
        return locale;
    }
}
