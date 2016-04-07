package pl.edu.uj.ii.webapp.execute;

/**
 * Created by gauee on 4/7/16.
 */
public class Param {
    private final SupportedLang supportedLang;
    private final UploadFile uploadFile;

    public Param(SupportedLang supportedLang, UploadFile uploadFile) {
        this.supportedLang = supportedLang;
        this.uploadFile = uploadFile;
    }

    public SupportedLang getSupportedLang() {
        return supportedLang;
    }

    public UploadFile getUploadFile() {
        return uploadFile;
    }

    @Override
    public String toString() {
        return "Param{" +
                "supportedLang=" + supportedLang +
                ", uploadFile=" + uploadFile +
                '}';
    }
}
