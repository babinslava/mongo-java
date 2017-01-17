package biz.bsoft;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Slava on 16.01.2017.
 */
public class HelloWorldSparkFreemarker {
    public static void main(String[] args) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

        Spark.get("/", (Request req, Response res) ->{
            StringWriter writer = new StringWriter();
            try {
                cfg.setDirectoryForTemplateLoading(new File("/projects/mongoJ/src/main/resources/"));
                Template template= cfg.getTemplate("index.ftl");
                Map<String, Object> helloMap = new HashMap<>();
                helloMap.put("name","bsoft.biz");
                template.process(helloMap,writer);
                //System.out.println(writer);
            }
            catch (Exception e)
            {
                Spark.halt(500, e.getMessage());
            }

            return  writer;
        });
    }
 }
