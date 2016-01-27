package junit.steps;

import junit.pages.LoginPage;
import net.thucydides.core.annotations.Step;

public class LoginSuccessfulSteps{

	LoginPage page;
	 
	 @Step("User Opens Login Page")
	 public void opens_loginpage(){
		 page.open();
	 }
	
	@Step("User Types in Username: {0} and Password: {1}")
	public void userTypesUserPass(String username, String password){
	 page.typeinUsernamePassword(username, password);
	}
			 
	@Step("User clicks Log in Button")
	 public void logs_in(){		
		page.login();
	 }
	 
	 @Step("User is shown the Welcome Page")
	 public void sees_welcomepage(){
		 page.welcomepage();
	 }
}