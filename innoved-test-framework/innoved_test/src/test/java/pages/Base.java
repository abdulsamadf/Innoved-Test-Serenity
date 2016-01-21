package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.pages.PageObject;

public class Base extends PageObject{
  

    public Boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        }
    }

    public Boolean waitForIsDisplayedByLocator(By locator, Integer... timeout) {
        try {
            waitForByLocator(ExpectedConditions.visibilityOfElementLocated(locator),
                    (timeout.length > 0 ? timeout[0] : null));
        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }
        return true;
    }

    
    private void waitForByLocator(ExpectedCondition<WebElement> condition, Integer timeout) {
        timeout = timeout != null ? timeout : 5;
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.until(condition);
    }
    

    
    public Boolean waitForIsDisplayed(By locator, String text, Integer... timeout) { 
		try {
		waitFor(ExpectedConditions.textToBePresentInElementLocated(locator, text), (timeout.length > 0 ? timeout[0] : null));
		} catch (org.openqa.selenium.TimeoutException exception) { return false;
		}
		return true; }
		
	private void waitFor(ExpectedCondition<Boolean> expectedCondition, Integer timeout) { timeout = timeout != null ? timeout : 5;
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout); wait.until(expectedCondition);
		} 
}