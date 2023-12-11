package api.endpoints;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class Userendpoints {
	
	public static Response createuser(User payload){
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)

		.when()
		.post(Routes.post_url);
		
		return response;
	}
	
	public static Response readuser(String username){
		Response response = given()
		.pathParam("username", username)
		
		.when()
		.get(Routes.get_url);
		
		return response;
	}
	
	public static Response updateuser(String username,User payload){
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", username)

		.when()
		.put(Routes.put_url);
		
		return response;
	}
	
	public static Response deleteuser(String username){
		Response response = given()
		.pathParam("username", username)
		
		.when()
		.delete(Routes.delete_url);
		
		return response;
	}

}
