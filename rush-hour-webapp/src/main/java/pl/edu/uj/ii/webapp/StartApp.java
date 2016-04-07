package pl.edu.uj.ii.webapp;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import pl.edu.uj.ii.webapp.execute.*;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

import static org.apache.commons.lang.StringUtils.isEmpty;
import static pl.edu.uj.ii.webapp.execute.SupportedLang.JAVA_8;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by gauee on 4/7/16.
 */
public class StartApp {
    public static final String PARAM_SUPPORTED_LANG = "supportedLang";
    public static final String PARAM_FILE_CONTENT = "fileContent";
    private static final Logger LOGGER = Logger.getLogger(StartApp.class);
    public static Properties appProperties;
    private RushHourExecutor rushHourExecutor;

    public static void main(String[] args) throws IOException {
        StartApp startApp = new StartApp();
        startApp.init();
    }

    private void init() {
        appProperties = loadAppProperties();
        initRoutes();
        initExecutor();
    }

    private Properties loadAppProperties() {
        Properties appProperties = new Properties();
        try {
            appProperties.load(getClass().getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException e) {
            LOGGER.warn("Cannot load app properties.", e);
        }

        return appProperties;
    }

    private void initExecutor() {
        TaskFactory taskFactory = new TaskFactory(initLanguageCompilers());
        rushHourExecutor = new RushHourExecutor(taskFactory);
    }


    private void initRoutes() {
        VelocityTemplateEngine templateEngine = new VelocityTemplateEngine();
        get("/", (req, res) -> uploadPageView(), templateEngine);
        post("/submit", "multipart/form-data", (req, res) -> processNewSolution(req), templateEngine);
    }

    private Map<SupportedLang, Compilable> initLanguageCompilers() {
        Map<SupportedLang, Compilable> languageCompilers = Maps.newHashMap();
        languageCompilers.put(SupportedLang.JAVA_7, new JavaTask());
        languageCompilers.put(SupportedLang.JAVA_8, new JavaTask());
        languageCompilers.put(SupportedLang.PYTHON, new PythonTask());
        return languageCompilers;
    }

    private ModelAndView processNewSolution(Request req) {
        Param param = createParam(req);
        LOGGER.debug("Request param: " + param);
        rushHourExecutor.resolveAllTestCases(param);
        return tweakSubmit(uploadPageView());
    }

    private Param createParam(Request req) {
        SupportedLang supportedLang = retrieveSupportedLang(req);
        UploadFile uploadFile = retrieveSourceCode(req);
        return new Param(supportedLang, uploadFile);
    }

    private ModelAndView tweakSubmit(ModelAndView modelAndView) {
        ((Map<String, Object>) modelAndView.getModel()).put("message", "File uploaded.");
        return modelAndView;
    }


    private UploadFile retrieveSourceCode(Request req) {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("./target");
        req.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
        StringBuilder sourceCodeBuilder = new StringBuilder();
        String fileName = StringUtils.EMPTY;
        try {
            Part part = req.raw().getPart(PARAM_FILE_CONTENT);
            fileName = part.getSubmittedFileName();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(part.getInputStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sourceCodeBuilder.append(line).append('\n');
                }
            }
        } catch (ServletException | IOException e) {
            LOGGER.info("Cannot load file.", e);
        }
        return new UploadFile(fileName, sourceCodeBuilder.toString());
    }

    private SupportedLang retrieveSupportedLang(Request req) {
        String langIdx = req.queryParams(PARAM_SUPPORTED_LANG);
        if (isEmpty(langIdx)) {
            return JAVA_8;
        }
        int idx = Integer.valueOf(langIdx);
        SupportedLang[] values = SupportedLang.values();
        return idx >= 0 && idx < values.length ? values[idx] : JAVA_8;
    }

    private ModelAndView uploadPageView() {
        Map<String, Object> model = Maps.newHashMap();
        model.put("supportedLang", SupportedLang.values());
        return new ModelAndView(model, "templates/upload.vm");
    }


}
