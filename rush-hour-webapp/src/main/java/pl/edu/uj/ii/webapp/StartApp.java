package pl.edu.uj.ii.webapp;

import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import pl.edu.uj.ii.webapp.execute.SupportedLang;
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

    public static void main(String[] args) throws IOException {
        get("/", (req, res) -> uploadPageView(), new VelocityTemplateEngine());
        post("/submit", "multipart/form-data", (req, res) -> processNewSolution(req), new VelocityTemplateEngine());
    }

    private static ModelAndView processNewSolution(Request req) {
        SupportedLang supportedLang = retrieveSupportedLang(req);
        String sourceCode = retrieveSourceCode(req);
        LOGGER.debug("Request params: " + supportedLang + ", SourceCode=" + sourceCode);

        ModelAndView modelAndView = uploadPageView();
        ((Map<String, Object>) modelAndView.getModel()).put("message", "File uploaded.");
        return modelAndView;
    }

    private static String retrieveSourceCode(Request req) {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
        req.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
        StringBuilder sourceCodeBuilder = new StringBuilder();
        try {
            Part part = req.raw().getPart(PARAM_FILE_CONTENT);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(part.getInputStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sourceCodeBuilder.append(line);
                }
            }
        } catch (ServletException | IOException e) {
            LOGGER.info("Cannot load file.", e);
        }
        return sourceCodeBuilder.toString();
    }

    private static SupportedLang retrieveSupportedLang(Request req) {
        String langIdx = req.queryParams(PARAM_SUPPORTED_LANG);
        if (isEmpty(langIdx)) {
            return JAVA_8;
        }
        int idx = Integer.valueOf(langIdx);
        SupportedLang[] values = SupportedLang.values();
        return idx >= 0 && idx < values.length ? values[idx] : JAVA_8;
    }

    private static ModelAndView uploadPageView() {
        Map<String, Object> model = Maps.newHashMap();
        model.put("supportedLang", SupportedLang.values());
        return new ModelAndView(model, "templates/upload.vm");
    }

}
