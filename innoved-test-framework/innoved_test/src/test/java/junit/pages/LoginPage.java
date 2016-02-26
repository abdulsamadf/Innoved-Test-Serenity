package junit.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.support.FindBy;

import junit.pages.Base;

import java.io.IOException;  
import java.net.HttpURLConnection;  
import java.net.MalformedURLException;  
import java.net.URL;  
import java.util.List;  
import java.util.concurrent.TimeUnit;  
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;  

@DefaultUrl("http://vle.innovedv2api.vm")    

public class LoginPage extends Base{

	 @FindBy(css="button.btn.btn-primary")
	    WebElementFacade loginButton;
	 
	 Base base;
	 By usernameLocator  = By.xpath("//div[1]/div/input");
	 By passwordLocator  = By.xpath("//div[2]/div/input");
	 By successLocator = By.xpath("//li[3]/a"); 
	 By failureMessageLocator = By.xpath("//div[2]/form/div[1]");

	 
	public void type(String inputText, By locator) {
	        find(locator).sendKeys(inputText);
	}
	 
	public void typeinUsernamePassword(String username, String password) {
		 type(username, usernameLocator);
	     type(password, passwordLocator);	 
	}
 
	public void login() {
	        loginButton.click();
	}
	 
	public boolean welcomepage(){
		return base.waitForIsDisplayedByLocator(successLocator, 5);
	}
	
	public boolean loginfailed(){
		return base.isDisplayed(failureMessageLocator);
	}
	
	public Boolean loginfailedTextPresent() {
		return base.waitForIsDisplayed(failureMessageLocator,"Error: Invalid Username or Password.", 3);
	}
	
	

}




	

