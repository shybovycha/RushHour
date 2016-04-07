package pl.edu.uj.ii.webapp.execute;

/**
 * Created by gauee on 4/7/16.
 */
public class Param {
    private final SupportedLang supportedLang;
    private final String sourceCode;

    public Param(SupportedLang supportedLang, String sourceCode) {
        this.supportedLang = supportedLang;
        this.sourceCode = sourceCode;
    }

    public SupportedLang getSupportedLang() {
        return supportedLang;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    @Override
    public String toString() {
        return "Param{" +
                "supportedLang=" + supportedLang +
                ", sourceCode='" + sourceCode + '\'' +
                '}';
    }
}
