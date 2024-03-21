import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

public class JiraTest {

	public static void main(String[] args) {

		RestAssured.baseURI = "http://localhost:8080";
		SessionFilter session = new SessionFilter();
		given().log().all().body("{ \"username\": \"reachprudhvig\", \"password\": \"marketinginc123\" }")
				.header("content-type", "application/json").filter(session).when().post("/rest/auth/1/session").then()
				.assertThat().statusCode(200);

		given().pathParam("key", "10003")
				.body("{\n" + "    \"body\": \"Adding comment to the bug\",\n" + "    \"visibility\": {\n"
						+ "        \"type\": \"role\",\n" + "        \"value\": \"Administrators\"\n" + "    }\n" + "}")
				.header("content-type", "application/json").log().all().filter(session).when()
				.post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201);

	}

}
