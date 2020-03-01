package testng_package;



	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.By;
	import org.openqa.selenium.NoSuchElementException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.FluentWait;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import com.google.common.base.Function;

	public class Amazon {
		
		private static String URL="https://www.amazon.in";
		public static WebDriver driver;
		// search class name
		private static final String sClass = "nav-input";
		
		public void initWebDriver(String driverPath) throws InterruptedException {

			// Setting up Chrome_driver path.
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
			// Launching Chrome_browser.
			driver = new ChromeDriver();
			driver.get(URL);
			driver.manage().window().maximize();
		}
		
		public void searchItem(String iPhoneModelKey) {
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys(iPhoneModelKey);
			WebElement searchResult = getElement(By.className(sClass));
			searchResult.click();
		}
		
		public void selectItem(String searchStirng) throws InterruptedException {
			
			String iPhoneModel = "(//a[contains(.,'" + searchStirng + "')])[last()]";
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(iPhoneModel))).click();
			Thread.sleep(1000);
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}
			Thread.sleep(1000);
		}
		
		public String getPriceXpath (String name) {
			String price = driver.findElement(By.xpath(name)).getText();
			//System.out.println(price);
			return price;
		}

		public static WebElement getElement(final By locator) {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

			WebElement element = wait.until(new Function<WebDriver, WebElement>() {

				@Override
				public WebElement apply(WebDriver arg0) {
					return arg0.findElement(locator);
				}

			});

			return element;
		}

		public void endSession() {
			driver.close();
			driver.quit();
		}
	}

