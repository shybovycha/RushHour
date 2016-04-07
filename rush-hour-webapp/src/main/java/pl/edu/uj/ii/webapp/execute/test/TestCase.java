package pl.edu.uj.ii.webapp.execute.test;

import java.io.File;

/**
 * Created by gauee on 4/7/16.
 */
public class TestCase {
    private final String id;
    private final File file;

    public TestCase(String id, File file) {
        this.id = id;
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public File getFile() {
        return file;
    }
}
