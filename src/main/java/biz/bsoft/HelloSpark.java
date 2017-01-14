package biz.bsoft;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Slava on 13.01.2017.
 */
public class HelloSpark {
    public static void main(String[] args) {
        Spark.get("/",(req,res)->"Hello");
    }
}
