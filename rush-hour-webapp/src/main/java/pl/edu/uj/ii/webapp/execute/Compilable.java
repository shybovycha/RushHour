package pl.edu.uj.ii.webapp.execute;

import java.io.IOException;

/**
 * Created by gauee on 4/7/16.
 */
public interface Compilable {

    Task compile(UploadFile uploadFile) throws IOException, ClassNotFoundException;
}
