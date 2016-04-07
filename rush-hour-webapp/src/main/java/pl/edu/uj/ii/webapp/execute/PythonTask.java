package pl.edu.uj.ii.webapp.execute;

import pl.edu.uj.ii.model.CarMove;
import pl.edu.uj.ii.webapp.execute.test.TestCase;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by gauee on 4/7/16.
 */
public class PythonTask implements Compilable, Task {

    @Override
    public Task compile(UploadFile uploadFile) throws IOException, ClassNotFoundException {
        return this;
    }

    @Override
    public List<CarMove> resolveTestCases(TestCase testCase) {
        return Collections.emptyList();
    }
}
