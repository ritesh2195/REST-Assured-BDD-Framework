package stepDefinitions;

import Pojo.AddPlace;
import Pojo.AddPlaceLocation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.Assert.*;

public class addPlaceAPI extends Utils {

    RequestSpecification requestSpec;
    Response response;
    ResponseSpecification responseSpecification;
    String placeId;
    TestDataBuilder addPlace = new TestDataBuilder();

    @Given("Add Place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        requestSpec = given().spec(requestSpecification()).body(addPlace.addPlaceData(name,language,address));
    }

    @When("user calls {string} with http {string} request")
    public void user_calls_with_http_post_request(String resources,String method) {

        responseSpecification =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        APIResources apiResources = APIResources.valueOf(resources);

        String res =apiResources.getResources();

        if (method.equalsIgnoreCase("POST")) {

            response = requestSpec.when().post(res).then().spec(responseSpecification).extract().response();

        }

        else if (method.equalsIgnoreCase("GET")){

            response = requestSpec.when().get(res).then().spec(responseSpecification).extract().response();
        }
    }
    @Then("API call should be successful with status code {int}")
    public void api_call_should_be_successful_with_status_code(Integer int1) {

       assertEquals(response.getStatusCode(),200);

    }
    @And("{string} should be {string} in response body")
    public void should_be_in_response_body(String actual, String expected) {

        //String key = getJsonParse("place_id",response);

        String res = response.asString();

        JsonPath jsonPath = new JsonPath(res);

        assertEquals(jsonPath.getString(actual),expected);

        System.out.println(res);

        placeId = jsonPath.getString("place_id");

    }

    @And("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using_get_place_api(String expName, String resources ) throws IOException {

        requestSpec = given().spec(requestSpecification()).queryParam("place_id", placeId);

        APIResources apiResources = APIResources.valueOf(resources);

        response = requestSpec.when().post(apiResources.getResources()).then().spec(responseSpecification).extract().response();

        String string_response = response.asString();

        String actName = getJsonParse("name", response);

        assertEquals(expName,actName);

    }
}
