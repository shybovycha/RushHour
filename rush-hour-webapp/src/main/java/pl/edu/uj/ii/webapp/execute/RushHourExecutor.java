package pl.edu.uj.ii.webapp.execute;

import org.apache.log4j.Logger;
import pl.edu.uj.ii.webapp.execute.test.TestCase;
import pl.edu.uj.ii.webapp.execute.test.TestResult;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static pl.edu.uj.ii.webapp.AppConfig.CONFIG;

/**
 * Created by gauee on 4/7/16.
 */
public class RushHourExecutor {
    private static final Logger LOGGER = Logger.getLogger(RushHourExecutor.class);
    private final TaskFactory taskFactory;

    public RushHourExecutor(TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    public List<TestResult> resolveAllTestCases(final Param param) {
        try {
            Task task = taskFactory.resolveTask(param);
            return Files.list(Paths.get(getClass().getClassLoader().getResource(CONFIG.getTestCasesDir()).toURI()))
                    .filter(f -> f.toFile().getName().endsWith(".in"))
                    .map(path -> {
                        File file = path.toFile();
                        return new TestCase(file.getName(), file);
                    })
                    .map(testCase -> new TestResult(testCase.getId(), task.resolveTestCases(testCase)))
                    .collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            LOGGER.warn("Cannot execute code source " + param);
        } catch (ClassNotFoundException e) {
            LOGGER.warn("Cannot execute code " + param, e);
        }
        return emptyList();
    }
}
