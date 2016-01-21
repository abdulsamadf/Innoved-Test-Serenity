package features;

import steps.LoginSuccessfulSteps;
import steps.LoginUnsuccessfulSteps;
import org.junit.runner.RunWith;
import org.junit.Test;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;


@RunWith(SerenityRunner.class) 
public class TestUserLoginSuccessful {

	@Managed(driver="chrome")                              
    WebDriver driver;
	
	 @Steps                                                                       
	 LoginSuccessfulSteps user;
	 
	 @Test
	 public void user_logs_in_successfully(){
		 //GIVEN
	      user.opens_loginpage();
		 //WHEN
	     user.userTypesUserPass("innovedadmin", "innoved123");
		 user.logs_in();
		 //THEN
		 user.sees_welcomepage();
	 }
	 
	 @Steps
      LoginUnsuccessfulSteps user2;
	 
	 @Test
	 public void user_incorrect_password_or_username(){
		 //GIVEN
	      user2.opens_loginpage();
		 //WHEN
	     user2.userTypesUserPass("innovedadmin2", "innoved123");
		 user2.logs_in();
		 //THEN
		 user2.sees_incorrect_username_password_message();
	 }
	
}