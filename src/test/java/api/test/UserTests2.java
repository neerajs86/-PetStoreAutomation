package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker;
	User payload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		faker = new Faker();
		payload = new User();
		
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstname(faker.name().firstName());
		payload.setLastname(faker.name().lastName());
		payload.setPassword(faker.internet().password(5, 10));
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	 
	@Test(priority = 1)
	public void testPostUser() {
	logger.info("**********Creating User**********");
	Response response =	UserEndPoints2.createUser(payload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("**********User is created**********");
	}
	
	
	@Test(priority = 2)
	public void testGetUserByName() {
	logger.info("**********Reading User Info**********");
     Response response = UserEndPoints2.readUser(this.payload.getUsername());
     response.then().log().all();
     Assert.assertEquals(response.getStatusCode(), 200);
 	logger.info("**********User info is displayed**********");
	}
	
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
	logger.info("**********Updating user info**********");
	payload.setFirstname(faker.name().firstName());
	payload.setLastname(faker.name().lastName());
	payload.setEmail(faker.internet().emailAddress());
	Response response =  UserEndPoints2.updateUser(this.payload.getUsername(), payload);
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	
	Response responseAfterUpdate = UserEndPoints2.readUser(this.payload.getUsername());
	responseAfterUpdate.then().log().all();
	Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	logger.info("**********User is Updated**********");
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
	logger.info("**********Deleting user info**********");	
	Response response =	UserEndPoints2.deleteUser(this.payload.getUsername());
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("**********User is Deleted**********");
	}


}
