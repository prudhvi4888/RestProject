import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import files.ReusableMethods;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	// verify if sum of all course price matches with purchase amount
	@Test
	public void sumofCourse() {
		int totalPrice = 0;
		JsonPath js = ReusableMethods.rawToJson(Payload.mockResponse());
		int courseSize = js.getInt("courses.size()");
		
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		
		for (int i = 0; i < courseSize; i++) {
			int copies = js.getInt("courses[" + i + "].copies");
			int price = js.getInt("courses[" + i + "].price");
			int purchasePrice = price * copies;
			totalPrice += purchasePrice;
		}
		System.out.println("The sum of all course Expected price is " + totalPrice);
		System.out.println("The sum of all course Actual price is " + totalAmount);
		Assert.assertEquals(totalAmount, totalPrice);
	}

}
