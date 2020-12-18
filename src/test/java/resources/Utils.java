package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification req;
    Properties properties;

    public RequestSpecification requestSpecification() throws IOException {

        String baseURL = getGlobalProperties("url");

        if (req==null) {

            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

            RestAssured.baseURI = baseURL;

            req = new RequestSpecBuilder().setBaseUri(baseURL).addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log)).

            addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();

            return req;
        }

        return req;
    }

    public String getGlobalProperties(String key) throws IOException {

        if (properties == null) {

            File file = new File("src/test/java/resources/global.properties");

            FileInputStream fis = new FileInputStream(file);

            properties = new Properties();

            properties.load(fis);

            }
        return properties.getProperty(key);
    }

    public String getJsonParse(String key, Response response){

        String res = response.asString();

        JsonPath jsonPath = new JsonPath(res);

        return jsonPath.getString(key);

    }
}