package pl.edu.uj.ii.webapp.execute;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.util.Map;

/**
 * Created by gauee on 4/7/16.
 */
public class TaskFactory {

    private final Map<SupportedLang, Compilable> supportedTask = Maps.newHashMap();

    public Task resolveTask(Param param) throws IOException, ClassNotFoundException {
        return supportedTask.get(param.getSupportedLang()).compile(param.getSourceCode());
    }
}
