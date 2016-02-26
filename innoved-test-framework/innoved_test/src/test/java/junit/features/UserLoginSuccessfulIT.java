package junit.features;

import org.junit.runner.RunWith;
import org.junit.Test;

import java.io.IOException;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import org.openqa.selenium.WebDriver;

import junit.steps.LoginSuccessfulSteps;

@RunWith(SerenityRunner.class) 
public class UserLoginSuccessfulIT {

	@Managed(driver="chrome")                              
    WebDriver driver;
	
	@Steps                                                                       
	LoginSuccessfulSteps user;
	 
	@Test
	public void user_logs_in_successfully() throws IOException{
		//GIVEN
	    user.opens_loginpage();
		//WHEN
	    user.userTypesUserPass("innovedadmin", "innoved123");
	    user.logs_in();
		//THEN
		user.sees_welcomepage();
		user.check_for_dead_links_from_file();
		
	}
	 	
}