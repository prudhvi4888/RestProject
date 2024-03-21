package files;

public class Payload {

	public static String AddPlacePayload() {
		return "{\n" + "\"location\": {\n" + "\"lat\": -38.383494, \"lng\": 33.427362\n" + "},\n"
				+ "\"accuracy\": 50,\n"
				+ "\"name\": \"Frontline house\", \"phone_number\": \"(+91) 983 893 3937\", \"address\": \"29, side layout, cohen 09\", \"types\": [\n"
				+ "\"shoe park\",\n" + "\"shop\" ],\n" + "\"website\": \"http://rahulshettyacademy.com\",\n"
				+ "\"language\": \"French-IN\" }\n" + "";

	}

	public static String mockResponse() {

		return "{\n" + "\n" + "\"dashboard\": {\n" + "\n" + "\"purchaseAmount\": 910,\n" + "\n"
				+ "\"website\": \"rahulshettyacademy.com\"\n" + "\n" + "},\n" + "\n" + "\"courses\": [\n" + "\n" + "{\n"
				+ "\n" + "\"title\": \"Selenium Python\",\n" + "\n" + "\"price\": 50,\n" + "\n" + "\"copies\": 6\n"
				+ "\n" + "},\n" + "\n" + "{\n" + "\n" + "\"title\": \"Cypress\",\n" + "\n" + "\"price\": 40,\n" + "\n"
				+ "\"copies\": 4\n" + "\n" + "},\n" + "\n" + "{\n" + "\n" + "\"title\": \"RPA\",\n" + "\n"
				+ "\"price\": 45,\n" + "\n" + "\"copies\": 10\n" + "\n" + "}\n" + "\n" + "]\n" + "\n" + "}";
	}

	public static String Addbook(String isbn, String aisle) {
		// TODO Auto-generated method stub
		return null;
	}

}
