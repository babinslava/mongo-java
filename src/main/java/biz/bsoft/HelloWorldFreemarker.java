package biz.bsoft;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldFreemarker {
    public static void main(String[] args){
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        try {
            cfg.setDirectoryForTemplateLoading(new File("/projects/mongoJ/src/main/resources/"));
            Template template= cfg.getTemplate("index.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<>();
            helloMap.put("name","bsoft.biz");

            template.process(helloMap,writer);
            System.out.println(writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
