package pl.edu.uj.ii.webapp;

/**
 * Created by gauee on 4/7/16.
 */
public enum SupportedLang {
    JAVA_7("Java 7"),
    JAVA_8("Java 8"),
    PYTHON("Python 3");

    private final String description;

    SupportedLang(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
