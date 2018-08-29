package DemoPages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Registration {
	
	//Xpaths
	
	By btnBrowse = By.xpath(".//*[@id='imagesrc']");
	By btnSkipSignIn = By.xpath(".//*[@id='btn2']");
	By txtFirstName = By.xpath(".//*[text()='Full Name* ']//following::input[1]");
	By txtLastName = By.xpath(".//*[text()='Full Name* ']//following::input[2]");
	
	By txtAddress = By.xpath(".//*[text()='Address']//following::textarea[1]");
	By txtEmailAddress = By.xpath(".//*[text()='Email address*']//following::input[1]");
	
	By txtPhone = By.xpath(".//*[text()='Phone*']//following::input[1]");
	By rdoMGender = By.xpath(".//*[text()='Gender*']//following::input[1]");
	By rdoFGender = By.xpath(".//*[text()='Gender*']//following::input[1]");
	
	By chkCricketHobbies = By.xpath(".//*[text()='Hobbies']//following::input[1]");
	By chkMoviesHobbies = By.xpath(".//*[text()='Hobbies']//following::input[2]");
	By chkHockeyHobbies = By.xpath(".//*[text()='Hobbies']//following::input[3]");
	
	By drpLanguages = By.xpath(".//*[@id='msdd']");
	By drpSkills = By.xpath(".//*[text()='Skills']//following::select[1]");
	By drpCountry = By.xpath(".//*[text()='Country*']//following::select[1]");
	By drpSelectCountry = By.xpath(".//select[@id='country']");
	By drpDOBYear = By.xpath(".//*[@id='yearbox']");
	By drpDOBMonth = By.xpath(".//*[text()='Date Of Birth']//following::select[2]");
	By drpDOBDay = By.xpath(".//*[text()='Date Of Birth']//following::select[3]");
	
	By txtPassword = By.xpath(".//*[@id='firstpassword']");
	By txtConfirmPwd = By.xpath(".//*[@id='secondpassword']");
	
	
	
	//Clicks on the skip login
	public void clickSkipLogin(WebDriver dr) {
		
		dr.findElement(btnSkipSignIn).click();
		
	}
	
	
	
	public void enterDetails(WebDriver dr,String tc,String fname ,String lname,String address) {
		
		System.out.println("Test Case - "+tc);
		dr.findElement(txtFirstName).sendKeys(fname);
		dr.findElement(txtLastName).sendKeys(lname);
		
		dr.findElement(txtAddress).sendKeys(address);
		dr.findElement(txtEmailAddress).sendKeys("rohitrnjn@gmail.com");
		dr.findElement(txtPhone).sendKeys("98799989796");
		dr.findElement(rdoFGender).click();
		dr.findElement(chkCricketHobbies).click();
		dr.findElement(chkMoviesHobbies).click();
		
		
		//dr.findElement(drpLanguages).click();
		
//		Actions action = new Actions()
//				action.moveToElement(target)
	
		
		
		Select sel = new Select(dr.findElement(drpSkills));
		sel.selectByVisibleText("Java");
		
		Select sel1 = new Select(dr.findElement(drpCountry));
		sel1.selectByVisibleText("India");
		
		Select sel2 = new Select(dr.findElement(drpSelectCountry));
		sel2.selectByVisibleText("India");
		
		Select sel3 = new Select(dr.findElement(drpCountry));
		sel3.selectByVisibleText("India");
		
		Select sel4 = new Select(dr.findElement(drpDOBYear));
		sel4.selectByVisibleText("1991");
		
		Select sel5 = new Select(dr.findElement(drpDOBMonth));
		sel5.selectByVisibleText("January");
		
		Select sel6 = new Select(dr.findElement(drpDOBDay));
		sel6.selectByVisibleText("24");
		
		dr.findElement(txtPassword).sendKeys("Rohit");
		dr.findElement(txtConfirmPwd).sendKeys("Rohit");
		
		System.out.println(" Registration Details entered ");
			
		
	}

	public void browseFile(WebDriver dr) throws AWTException {
		
		dr.findElement(btnBrowse).click();
		
		String filename = "C:\\Users\\rr72165\\Desktop\\Onboarding Error Screenshots\\New folder (2)\\DOB.PNG";
		
		setClipboardData(filename);
		System.out.println("File name entered ");
		
		uploadFile(filename);
			
	}
	
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
	public static void uploadFile(String fileLocation) {
        try {
        	//Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }catch (Exception exp) {
        	exp.printStackTrace();
        }
	}
}
