package junit.steps;

import java.io.IOException;

import junit.pages.Base;
import junit.pages.LoginPage;
import net.thucydides.core.annotations.Step;

public class LoginSuccessfulSteps{

	LoginPage page;
	Base check;
	 
	 @Step("User Opens Login Page")
	 public void opens_loginpage(){
		 page.open();
	 }
	
	@Step("User Types in Username: '{0}' and Password: '{1}'")
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

	 @Step("Check for dead links")
	 public void check_for_dead_links_from_file() throws IOException{
		 check.validatelinksfromfile();
	 }
}
