package pl.edu.uj.ii.webapp.execute;

import com.google.common.collect.Lists;
import pl.edu.uj.ii.webapp.execute.test.TestCase;
import pl.edu.uj.ii.webapp.execute.test.TestResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gauee on 4/7/16.
 */
public class RushHourExecutor {

    private final List<TestCase> testCases = Lists.newLinkedList();
    private final TaskFactory taskFactory;

    public RushHourExecutor(TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    public List<TestResult> resolveAllTestCases(Param param) {
        Task task = taskFactory.resolveTask(param);
        List<TestResult> result = Lists.newLinkedList();
        testCases.stream()
                .map(testCase -> new TestResult(testCase.getId(), task.resolveTestCases(testCase)))
                .collect(Collectors.toCollection(() -> result));
        return result;
    }
}
