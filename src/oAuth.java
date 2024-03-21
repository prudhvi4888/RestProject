import static io.restassured.RestAssured.given;
import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import org.testng.Assert;

import Pojo.Api;
import Pojo.GetCourse;
import Pojo.WebAutomation;
import io.restassured.parsing.Parser;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;

import io.restassured.response.ResponseBody;

public class oAuth {

	public static void main(String[] args) {
		String[] WA_expectedList = { "Selenium Webdriver Java", "Cypress", "Protractor" };
		String response =

				given()

						.formParams("client_id",
								"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

						.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

						.formParams("grant_type", "client_credentials")

						.formParams("scope", "trust")

						.when().log().all()

						.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

		System.out.println("------------" + response);

		JsonPath jsonPath = new JsonPath(response);

		String accessToken = jsonPath.getString("access_token");

		System.out.println(accessToken);

		// After getting Json response convert it into Java Object by using the class
		// GetCourse with setters and getters
		GetCourse response2 = given()

				.queryParams("access_token", accessToken)

				.when()

				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		System.out.println(response2.getInstructor());
		System.out.println(response2.getLinkedIn());
		System.out.println(response2.getCourses().getApi().get(1).getPrice());
		List<Api> apiCourses = response2.getCourses().getApi();

		for (int i = 0; i < apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice());
			}
			// Print all courses in webAutomation
			ArrayList<String> actualCourseTitles = new ArrayList<String>();

			List<WebAutomation> WA_actualList = response2.getCourses().getWebAutomation();
			// for(int a=0; a<webAutomationList.size();a++) {
			for (WebAutomation wa : WA_actualList) {

				actualCourseTitles.add(wa.getCourseTitle());

			}

			List<String> expectedCourses = Arrays.asList(WA_expectedList);
			//Assert.assertTrue(expectedCourses.equals(actualCourseTitles));
			Assert.assertTrue(expectedCourses.equals(actualCourseTitles),
					"actaul course list doesn't match with expected courseList");
		}

	}
}
