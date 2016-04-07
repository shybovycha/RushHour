package pl.edu.uj.ii.webapp.execute;

import org.apache.commons.lang.StringUtils;
import pl.edu.uj.ii.webapp.StartApp;

import java.io.File;

/**
 * Created by gauee on 4/7/16.
 */
public enum SupportedLang implements Versionable {

    JAVA_7("Java 7 ") {
        @Override
        public String getVersion() {
            return StartApp.appProperties.getProperty("java.7.home");
        }
    },
    JAVA_8("Java 8") {
        @Override
        public String getVersion() {
            return StartApp.appProperties.getProperty("java.8.home");
        }
    },
    PYTHON("Python 3") {
        @Override
        public String getVersion() {
            return StartApp.appProperties.getProperty("python.3.home");
        }
    };

    private final String description;

    SupportedLang(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description + " (ver. " + StringUtils.substringAfterLast(getVersion(), File.separator) + " )";
    }


}
