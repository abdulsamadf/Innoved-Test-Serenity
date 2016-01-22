package junit.features;

import org.openqa.selenium.WebDriver;

import junit.pages.LoginPage;
import junit.steps.LoginUnsuccessfulSteps;

import org.junit.runner.RunWith;
import org.junit.Test;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;


@RunWith(SerenityRunner.class) 
public class UserLoginUnsuccessfulIT {

	@Managed(driver="chrome")                              
    WebDriver driver;
	 LoginPage page;

	 @Steps
      LoginUnsuccessfulSteps user;
	 
	 @Test
	 public void user_incorrect_password_or_username(){
		 //GIVEN
	     user.opens_loginpage();
		 //WHEN
	     user.userTypesUserPass("innovedadmin", "innoved123");
		 user.logs_in();
		 //THEN
	     user.sees_incorrect_username_password_message();

	 }
	
}