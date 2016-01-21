package pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;

import pages.Base;


@DefaultUrl("http://vle.innovedv2api.vm")    

public class LoginPage extends PageObject{

	 @FindBy(css="button.btn.btn-primary")
	    WebElementFacade loginButton;
	 
	 Base base;
	 By usernameLocator  = By.xpath("//div[1]/div/input");
	 By passwordLocator  = By.xpath("//div[2]/div/input");
	 By successLocator = By.xpath("//li[3]/a"); 
	 public By failureMessageLocator = By.xpath("//div[2]/form/div[1]");

	 
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
		return base.isDisplayed(successLocator);
	}
	
	public boolean loginfailed(){
		return base.isDisplayed(failureMessageLocator);
	}
	
	public Boolean loginfailedTextPresent() {
		return base.waitForIsDisplayed(failureMessageLocator,"Error: Invalid Username or Password.", 3);
		}
	

	}




	

