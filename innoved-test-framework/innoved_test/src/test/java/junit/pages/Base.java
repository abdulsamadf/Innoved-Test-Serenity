package junit.pages;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.pages.PageObject;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class Base extends PageObject{
	private int invalidLinksCount;
	
	public void clickBy(By locator) { 
		find(locator).click();
	}
	
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
		return true; 
	}
		
	private void waitFor(ExpectedCondition<Boolean> expectedCondition, Integer timeout) { 
		timeout = timeout != null ? timeout : 5;
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout); wait.until(expectedCondition);
	} 
	
	public void validatelinksfromfile() throws IOException{
		List<String> links_in_file = FileUtils.readLines(new File("/Users/a.s.farhat/Documents/testpages.txt"), "utf-8");
		String domain = new String("http://vle.innovedv2api.vm");
		System.out.println("Testing for invalid links on the following pages: " + links_in_file + "\n");
			for (int i = 0; i < links_in_file.size(); i++) {
					String url = new String(domain + links_in_file.get(i));
					System.out.println(url + " has a: ");
					getDriver().get(url);
					validateInvalidLinks();
			}	
	}
	
	public void validateInvalidLinks() {
	
		try {
			invalidLinksCount = 0;
			List<WebElement> anchorTagsList = getDriver().findElements(By.cssSelector("a[href]:not([href$=logout])"));
			System.out.println("Total no. of links of "
					+ anchorTagsList.size());
			
			for (WebElement anchorTagElement : anchorTagsList) {
				
				if (anchorTagElement != null ) {			
					String url = anchorTagElement.getAttribute("href");
					if (url != null && !url.contains("javascript")) {	
						verifyURLStatus(url);
						//System.out.println(url);
					} 
					else {
						invalidLinksCount++;
					}
				}
			}

			System.out.println("Total no. of invalid links = "
					+ invalidLinksCount 
					+ "\n");

		} 
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
		
	public void verifyURLStatus(String URL) {
	
		 //Get cookies using Selenium WebDriver
		 Object InnovedSessionName = getDriver().manage().getCookieNamed("InnovedSessionId").getName();
		 Object InnovedSessionValue = getDriver().manage().getCookieNamed("InnovedSessionId").getValue();
		 Object innovedApiSessionName = getDriver().manage().getCookieNamed("innovedApiSession").getName();
		 Object innovedApiSessionValue = getDriver().manage().getCookieNamed("innovedApiSession").getValue();
		 //Object InnovedSessionDate = getDriver().manage().getCookieNamed("InnovedSessionId").getExpiry();
		 //System.out.println(InnovedSessionDate);
		 //String cookies = (String) InnovedSessionName+"="+InnovedSessionValue+"; "+innovedApiSessionName+"="+innovedApiSessionValue;
		 
		 //Create CookieStore to use with HttpClient
		 BasicCookieStore cookieStore = new BasicCookieStore();
		 
		 BasicClientCookie cookie = new BasicClientCookie(InnovedSessionName.toString(),InnovedSessionValue.toString());
		    cookie.setDomain("vle.innovedv2api.vm");
		    cookie.setPath("/");
		    cookieStore.addCookie(cookie);
		 
		 BasicClientCookie cookie2 = new BasicClientCookie(innovedApiSessionName.toString(),innovedApiSessionValue.toString());
		    cookie2.setDomain("vle.innovedv2api.vm");
		    cookie2.setPath("/");
		    cookieStore.addCookie(cookie2);
		    //System.out.println(cookieStore);

		    //Set Proxy if required (i.e. for debugging etc)
//		    HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");

//            RequestConfig config = RequestConfig.custom()
//                    .setProxy(proxy)
//                    .build();
            //Change Cookie Specification
//		    RequestConfig globalConfig = RequestConfig.custom()
//		            .setCookieSpec(CookieSpecs.DEFAULT)
//		            .build();
//		   
//		    RequestConfig localConfig = RequestConfig.copy(globalConfig)
//		            .setCookieSpec(CookieSpecs.STANDARD_STRICT)
//		            .build();
		//Set-up HttpClient    	   
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(URL);
		
	    //Enable Proxy Config (see above to set details)
//		request.setConfig(config);
		//CookieSpecification if required
//		request.setConfig(localConfig);
		
		//Set Cookies to be used from the CookieStore
		HttpContext localContext = new BasicHttpContext();
	    localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
	    // localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore); // before v4.3
	    
	    //Low Level request setting of Cookies
		//request.setHeader("Cookie", cookies);
		try {
			HttpResponse response = client.execute(request,localContext);
			//System.out.println(response);
			//ResponseHandler<String> handler = new BasicResponseHandler();
			//String body = handler.handleResponse(response);
			//System.out.println(body);
			// verifying response code and The HttpStatus should be 200 if not,
			// increment invalid link count
			////We can also check for 404 status code like response.getStatusLine().getStatusCode() == 404
			if (response.getStatusLine().getStatusCode() != 200){
				 invalidLinksCount++;	
				 System.out.println("This link is invalid: " +"'"+ URL +"'" + " and returned Status Code: " + response.getStatusLine().getStatusCode());
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}