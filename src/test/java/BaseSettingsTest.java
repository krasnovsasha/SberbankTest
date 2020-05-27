import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseSettingsTest {
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static WebElement element;
	private static JavascriptExecutor executor;
	private static String OS;
	private static String driverChrome = "webdriver.chrome.driver";
	private static String driverFirefox = "webdriver.gecko.driver";
	private static String pathToDriverChromeLin = "drivers/driversLin/chromedriver";
	private static String pathToDriverChromeWin = "drivers/driversWin/chromedriver.exe";
	private static String pathToDriverChromeMac = "drivers/driversMac/chromedriver";
	private static String pathToDriverFirefoxLin = "drivers/driversLin/geckodriver";
	private static String pathToDriverFirefoxWin = "drivers/driversWin/geckodriver.exe";
	private static String pathToDriverFirefoxMac = "drivers/driversMac/geckodriver";
	protected String urlSBMainPage = "http://www.sberbank.ru/ru/person";
	protected String insuranceMainPageXPath = "//span[text()='Страхование']";
	protected String insuranceForTravellersXPath = "//a[@class='lg-menu__sub-link' and text()='Страхование путешественников']";

	private enum Browser {
		CHROME, FIREFOX
	}

	@BeforeClass
	public static void setUp() throws Exception {
		chooseBrowser(Browser.CHROME);
		wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}
	private static void chooseBrowser(Browser browser) {
		switch (browser) {
			case CHROME:
				setUpBrowser(driverChrome);
				driver = new ChromeDriver();
				break;
			case FIREFOX:
				setUpBrowser(driverFirefox);
				driver = new FirefoxDriver();
				break;
		}
	}
	private static void setUpBrowser(String driverBrowserName) {
		OS = System.getProperty("os.name").toLowerCase();
		String[] osName = OS.split("\\s");
		switch (osName[0]) {
			case "linux":
				switch (driverBrowserName) {
					case "webdriver.chrome.driver":
						System.setProperty(driverBrowserName, pathToDriverChromeLin);
						break;
					case "webdriver.gecko.driver":
						System.setProperty(driverBrowserName, pathToDriverFirefoxLin);
						break;
				}
				break;
			case "windows":
				switch (driverBrowserName) {
					case "webdriver.chrome.driver":
						System.setProperty(driverBrowserName, pathToDriverChromeWin);
						break;
					case "webdriver.gecko.driver":
						System.setProperty(driverBrowserName, pathToDriverFirefoxWin);
						break;
				}
				break;
			case "mac":
				switch (driverBrowserName) {
					case "webdriver.chrome.driver":
						System.setProperty(driverBrowserName, pathToDriverChromeMac);
						break;
					case "webdriver.gecko.driver":
						System.setProperty(driverBrowserName, pathToDriverFirefoxMac);
						break;
				}
				break;
		}
	}

	protected void goToPage(String page) {
		driver.get(page);
	}

	protected void clickByXPath(String XPath) {
		driver.findElement(By.xpath(XPath)).click();
	}

	protected void clickByID(String ID) {
		driver.findElement(By.id(ID)).click();
	}

	protected void waitUntilClickable(String XPath){
		WebElement element = driver.findElement(By.xpath(XPath));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	protected void waitUntilVisible(String XPath){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPath)));
	}
	protected  void waitUntilPresent(String XPath){
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
	}

	protected void clickByJS(String XPath){
		element = driver.findElement(By.xpath(XPath));
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();",element);
	}
}
