package pl.edu.uj.ii.webapp.execute;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import pl.edu.uj.ii.webapp.execute.test.TestCase;
import pl.edu.uj.ii.webapp.execute.test.TestResult;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gauee on 4/7/16.
 */
public class RushHourExecutor {
    private static final Logger LOGGER = Logger.getLogger(RushHourExecutor.class);
    private final List<TestCase> testCases = Lists.newLinkedList();
    private final TaskFactory taskFactory;

    public RushHourExecutor(TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    public List<TestResult> resolveAllTestCases(final Param param) {
        List<TestResult> result = Lists.newLinkedList();
        try {
            Task task = taskFactory.resolveTask(param);
            testCases.stream()
                    .map(testCase -> new TestResult(testCase.getId(), task.resolveTestCases(testCase)))
                    .collect(Collectors.toCollection(() -> result));
        } catch (IOException e) {
            LOGGER.warn("Cannot execute code source " + param);
        } catch (ClassNotFoundException e) {
            LOGGER.warn("Cannot execute code " + param, e);
        }
        return result;
    }
}
