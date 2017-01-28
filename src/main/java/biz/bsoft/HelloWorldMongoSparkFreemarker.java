package biz.bsoft;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.bson.Document;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.File;
import java.io.StringWriter;

public class HelloWorldMongoSparkFreemarker {
    public static void main(String[] args) {
        MongoClient  client=new MongoClient();
        MongoDatabase database= client.getDatabase("test");
        MongoCollection<Document> collection=database.getCollection("names");
        collection.drop();
        collection.insertOne(new Document("name","bsoft.biz"));

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

        Spark.get("/", (Request req, Response res) ->{
            StringWriter writer = new StringWriter();
            try {
                cfg.setDirectoryForTemplateLoading(new File("/projects/mongoJ/src/main/resources/"));
                Template template= cfg.getTemplate("index.ftl");
                Document document = collection.find().first();
                template.process(document,writer);
            }
            catch (Exception e)
            {
                Spark.halt(500, e.getMessage());
            }

            return  writer;
        });
    }
}
