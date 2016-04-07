package pl.edu.uj.ii.webapp.execute;

import java.io.IOException;
import java.util.Map;

/**
 * Created by gauee on 4/7/16.
 */
public class TaskFactory {

    private final Map<SupportedLang, Compilable> supportedTask;

    public TaskFactory(Map<SupportedLang, Compilable> supportedTask) {
        this.supportedTask = supportedTask;
    }

    public Task resolveTask(Param param) throws IOException, ClassNotFoundException {
        return supportedTask.get(param.getSupportedLang()).compile(param.getUploadFile());
    }
}
