package pl.edu.uj.ii.webapp.execute;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by gauee on 4/7/16.
 */
public class TaskFactory {

    private final Map<SupportedLang, Task> supportedTask = Maps.newHashMap();

    public Task resolveTask(Param param) {
        return supportedTask.get(param.getSupportedLang());
    }
}
