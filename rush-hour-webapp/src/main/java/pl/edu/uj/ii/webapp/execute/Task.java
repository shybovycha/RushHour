package pl.edu.uj.ii.webapp.execute;

import pl.edu.uj.ii.model.CarMove;
import pl.edu.uj.ii.webapp.execute.test.TestCase;

import java.util.List;

/**
 * Created by gauee on 4/7/16.
 */
public interface Task {
    List<List<CarMove>> resolveTestCases(TestCase testCase);
}
