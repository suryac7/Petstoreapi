package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Userendpoints;
import api.payload.User;
import io.restassured.response.Response;

public class Usertest {
	
	Faker faker;
	User userpayload;
	
	@BeforeClass
	public void setupDate()
	{
		faker = new Faker();
		userpayload = new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
	}
	@Test(priority = 1)
	public void testPostuser() {
		Response response = Userendpoints.createuser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority = 2)
	public void testgetuser() {
		Response response = Userendpoints.readuser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority = 3)
	public void testupdateuser() {
		//update a payload data
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = Userendpoints.updateuser(this.userpayload.getUsername(),userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		//Checking data after update
		Response responseafter = Userendpoints.readuser(this.userpayload.getUsername());
		Assert.assertEquals(responseafter.getStatusCode(),200);
	}
	@Test(priority = 4)
	public void testdeleteuser() {
		Response response=Userendpoints.deleteuser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}


}
