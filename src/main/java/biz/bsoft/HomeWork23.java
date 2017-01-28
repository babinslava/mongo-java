package biz.bsoft;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Sorts.ascending;

/**
 * Created by vbabin on 1/29/2017.
 */
public class HomeWork23 {
    public static void main(String[] args) {
        MongoCollection<Document> collection = new MongoClient().getDatabase("students").getCollection("grades");
        List<Document> documents = collection.find(new Document("type","homework")).sort(ascending("student_id","score")).into(new ArrayList<>());
        int prevStudent=0;
        int curStudent=-1;
        for(Document document:documents){
            curStudent=document.getInteger("student_id");
            if (curStudent!=prevStudent){
                collection.deleteOne(document);
                System.out.println(document);
            }
            prevStudent=curStudent;
        }
    }
}
