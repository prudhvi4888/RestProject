import org.testng.Assert;

import files.Payload;
import files.ReusableMethods;
import io.restassured.path.json.JsonPath;

public class ComplexJsonparse {

	public static void main(String[] args) {
		
		JsonPath js = ReusableMethods.rawToJson(Payload.mockResponse());
		int courseSize = js.getInt("courses.size()");
		System.out.println(courseSize);
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);

		// print title of first course

		System.out.println(js.getString("courses[0].title"));

		// print all course titles and respective prices
		for (int i = 0; i < courseSize; i++) {
			System.out.println(js.getString("courses[" + i + "].title"));
			System.out.println(js.getInt("courses[" + i + "].price"));

		}
		// No.of copies sold by RPA course
		for (int i = 0; i < courseSize; i++) {
			if (js.getString("courses[" + i + "].title").equalsIgnoreCase("RPA")) {
				System.out.println("Total copies sold by RPA course are " + js.getInt("courses[" + i + "].copies"));
				break;
			}
		}

	}
}
