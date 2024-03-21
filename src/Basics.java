import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.Payload;
import files.ReusableMethods;

public class Basics {

	public static void main(String[] args) throws IOException {
//Add place-> update the place with new address-> Get place to validate if new address is present in response
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// Adding address
		String addResponse = given().log().all().queryParam("key", "qaclick123").body(new String(Files.readAllBytes(Paths.get("/Users/prudhvirajgollapalli/Documents/Payload/Body.json")))).when()
				.post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();

		JsonPath js = ReusableMethods.rawToJson(addResponse);
		String placeId = js.getString("place_id");
		System.out.println(placeId);

		// Updating address
		String newAddress = "1811 Metzerott";
		given().log().all().queryParam("key", "qaclick123")
				.body("{ \"place_id\":\"" + placeId + "\", \n" + "\"address\":\"" + newAddress + "\", \n"
						+ "\"key\":\"qaclick123\"\n" + "} ")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Validating new address
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
	}

}
