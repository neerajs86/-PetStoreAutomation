package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.*;
import io.restassured.response.Response;


public class DDTests {
	
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userId, String username, String fname, String lname, String useremail, String pwd, String ph) {
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(username);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setPassword(pwd);
		userPayload.setEmail(useremail);
		userPayload.setPhone(ph);
	
		Response response =	UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String username) {
		Response response = UserEndPoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
