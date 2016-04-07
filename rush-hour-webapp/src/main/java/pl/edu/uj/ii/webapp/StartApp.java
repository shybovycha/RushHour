package pl.edu.uj.ii.webapp;

import com.google.common.collect.Maps;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.io.IOException;

import static spark.Spark.get;

/**
 * Created by gauee on 4/7/16.
 */
public class StartApp {

    public static void main(String[] args) throws IOException {
        get("/", (req, res) -> uploadPageView(), new VelocityTemplateEngine());
//        post("/submit")
    }

    private static ModelAndView uploadPageView() {
        return new ModelAndView(Maps.newHashMap(), "templates/upload.vm");
    }

}
