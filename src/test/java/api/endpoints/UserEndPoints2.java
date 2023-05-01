package api.endpoints;	
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created for performing CRUD [Create, Read, Update, Delete] operations

public class UserEndPoints2 {

	//Method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //Load properties file //Name of the properties file
		return routes;
	}
	
	
	public static Response createUser(User payload) {
	String post_url = getURL().getString("post_url");
	Response response =	given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		//.post(Routes.post_url);
		.post(post_url);
	return response;
	}
	
	
	public static Response readUser(String userName) {
	String get_url = getURL().getString("get_url");
	Response response =	given()
			.pathParam("username", userName)
		.when()
		//.get(Routes.get_url);
		.get(get_url);
	return response;
	}
	
	
	public static Response updateUser(String userName, User payload) {
		String update_url = getURL().getString("update_url");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)
		 .when()
		 //.put(Routes.update_url);
		 .put(update_url);
		return response;
	}

	
	
	public static Response deleteUser(String userName) {
	String delete_url = getURL().getString("delete_url");	
	Response response =	given()
			.pathParam("username", userName)
		.when()
		//.delete(Routes.delete_url);
		.delete(delete_url);
	return response;
	}
	
}
