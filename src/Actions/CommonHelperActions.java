package Actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonHelperActions {
	
	public static <T> void waitForPageToLoad(WebDriver driver) {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return      document.readyState")
						.equals("complete");
			}
		};

		Wait<WebDriver> wait = new WebDriverWait(driver, 60);
		wait.until((Function<? super WebDriver, T>) expectation);
	}

	public static void wait(WebDriver driver, final By elm){		
		new WebDriverWait(driver, 60).until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(elm);
			}
		});
	}

	public static void waitForClickable(WebDriver driver, final By elm){		
		new WebDriverWait(driver, 60).until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				try{ 
					driver.findElement(elm).isEnabled();


				}catch(Exception e){
				}
				return driver.findElement(elm);
			}
		});
	}


	public static void waitForClickableByelement(WebDriver driver, final WebElement elm){		
		new WebDriverWait(driver, 60).until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				try{ 
					elm.isEnabled();

				}catch(Exception e){

				}
				return elm;
			}
		});
	}


	public static void staticWait(WebDriver driver, int miliSces){	
		try {
			Thread.sleep(miliSces);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}



	public static String getRandomAlphaNumericString(int length) {


		char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9' };
		Random randomNumberGenerator = new Random();
		char[] stringArr = new char[length];
		int thisRandomNumber = 0;
		for (int itr = 0; itr < length; itr++) {
			thisRandomNumber = randomNumberGenerator.nextInt(50);
			stringArr[itr] = letters[thisRandomNumber];
		}
		return new String(stringArr);
	}


	public static String getCurrentDate(WebDriver driver){
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
		Date date = new Date();
		return dateFormat.format(date);

	}

	public static void waitTillLoadingDisappears(WebDriver driver){
		List<WebElement> list= driver.findElements(By.className("sapMDialogSection"));
		int i=0;
		try{
			for(WebElement elm:list){
				System.out.println(elm.getAttribute("role"));
				if(elm.getAttribute("role")== null)
					staticWait(driver,1000);
				//if(driver.findElement(By.xpath("//*[contains(@class,'sapUiLocalBusyIndicator')]")).isEnabled());
				else {
					while(i<10){					   
						if(elm.getAttribute("role").equalsIgnoreCase("application")){
							staticWait(driver,500);
							i++;
						}						  
						else
							break;
					}
				}
			}
		}catch(Exception e){
			System.out.println("Loading image no more displayed");
		}
	}
	
	/**SENDS MAIL WITH TEST REPORT
	 * 
	 */
	public static void sendMail(){
		String fromAddress="JDIITSuccessFactorsQA@JohnDeere.com";
		String toAddress =  "rohitranjan@johndeere.com";
		String subject = "Automation Test Result";
		String message = "Please find attached automation test report of SF. \n\nRegards, \nSF Automation Team";
		String HOST = "mail.dx.deere.com";
		try {
			MultiPartEmail email = new MultiPartEmail();
			EmailAttachment emailAttach = new EmailAttachment();
			emailAttach.setPath(System.getProperty("user.dir")+"\\test-output\\EC_Report.html");
			emailAttach.setDisposition(EmailAttachment.ATTACHMENT);
			emailAttach.setName("SF Automation Report.html");
			email.setHostName(HOST);

			email.setSubject(subject);
			email.setMsg(message);
			email.setFrom(fromAddress);

			email.addTo(toAddress);
			email.attach(emailAttach);
			email.send();

		}catch(Exception ex){
			System.out.println("Unable to send email");
			System.out.println(ex);
		}
	}

}
