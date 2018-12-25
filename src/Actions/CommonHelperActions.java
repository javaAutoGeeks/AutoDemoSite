package Actions;

import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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

}
