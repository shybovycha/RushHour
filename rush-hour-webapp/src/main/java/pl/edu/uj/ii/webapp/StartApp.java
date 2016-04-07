package pl.edu.uj.ii.webapp;

import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import pl.edu.uj.ii.webapp.execute.SupportedLang;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by gauee on 4/7/16.
 */
public class StartApp {
    private static final Logger LOGGER = Logger.getLogger(StartApp.class);

    public static void main(String[] args) throws IOException {
        get("/", (req, res) -> uploadPageView(), new VelocityTemplateEngine());
        post("/submit", (req, res) -> processNewSolution(req));
    }

    private static Object processNewSolution(Request req) {
        LOGGER.debug("Request params: " + queryMapOfParams(req));
        return "Processing solution";
    }

    private static Map<String, String> queryMapOfParams(Request req) {
        HashMap<String, String> mapOfParams = Maps.newHashMap();
        for (String param : req.queryParams()) {
            mapOfParams.put(param, req.queryParams(param));
        }
        return mapOfParams;
    }

    private static ModelAndView uploadPageView() {
        Map<String, Object> model = Maps.newHashMap();
        model.put("supportedLang", SupportedLang.values());
        return new ModelAndView(model, "templates/upload.vm");
    }

}
