package pl.edu.uj.ii.webapp.execute.test;

import pl.edu.uj.ii.model.CarMove;

import java.util.List;

/**
 * Created by gauee on 4/7/16.
 */
public class TestResult {
    private final String testCaseId;
    private final List<CarMove> moves;

    public TestResult(String testCaseId, List<CarMove> moves) {
        this.testCaseId = testCaseId;
        this.moves = moves;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public List<CarMove> getMoves() {
        return moves;
    }
}
