package PojoMaps;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SpecBuilderExample {

	public static void main(String[] args) {

		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress("29, side layout, cohen 09");
		ap.setWebsite("http://google.com");
		ap.setLanguage("French-IN");

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);

		List<String> type = new ArrayList<String>();
		type.add("shoe park");
		type.add("shop");
		ap.setTypes(type);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
		new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();

		// RestAssured.baseURI = "https://rahulshettyacademy.com";
		RequestSpecification rs = given().log().all().spec(req).body(ap);
	Response response=	rs.when().post("/maps/api/place/add/json").then().assertThat()
				.statusCode(200).extract().response();
		String rsString = response.asString();
		System.out.println(rsString);
	}

}
