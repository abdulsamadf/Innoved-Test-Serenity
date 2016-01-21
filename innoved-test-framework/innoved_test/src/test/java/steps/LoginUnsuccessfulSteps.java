package steps;

import static org.junit.Assert.assertTrue;

import net.thucydides.core.annotations.Step;
import pages.LoginPage;

public class LoginUnsuccessfulSteps {

	LoginPage page;
	 
	 @Step("User Opens Login Page")
	 public void opens_loginpage(){
		 page.open();
	 }
	
	@Step("User Types in Username and Password")
	public void userTypesUserPass(String username, String password){
	 page.typeinUsernamePassword(username, password);
	}
			 
	@Step("User clicks Log in Button")
	 public void logs_in(){		
		page.login();
	 }
	 
	 @Step("User is shown 'Error: Incorrect Username or Password' message")
	 public void sees_incorrect_username_password_message(){
		 assertTrue("Failure Message Wasn't Present after Providing Bogus Credentials",
				 page.loginfailedTextPresent());
	 }
	
	
}
