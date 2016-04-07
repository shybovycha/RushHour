package pl.edu.uj.ii.webapp.execute;

import org.apache.commons.lang.StringUtils;

/**
 * Created by gauee on 4/7/16.
 */
public class UploadFile {
    private final String name;
    private final String data;

    public UploadFile(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "UploadFile{" +
                "name='" + name + '\'' +
                ", data='" + StringUtils.replaceChars(data, '\n', ' ') + '\'' +
                '}';
    }
}