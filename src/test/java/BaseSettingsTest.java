import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseSettingsTest {
	private static WebDriver driver;
	private static WebDriverWait wait;
	protected static NavigateTest navigator;
	private static String OS;
	private final static String driverChrome = "webdriver.chrome.driver";
	private final static String driverFirefox = "webdriver.gecko.driver";
	private final static String pathToDriverChromeLin = "drivers/driversLin/chromedriver";
	private final static String pathToDriverChromeMac = "drivers/driversMac/chromedriver";
	private final static String pathToDriverFirefoxLin = "drivers/driversLin/geckodriver";
	private final static String pathToDriverFirefoxWin = "drivers/driversWin/geckodriver.exe";
	private final static String pathToDriverFirefoxMac = "drivers/driversMac/geckodriver";

	public static WebDriver getDriver() {
		return driver;
	}

	public static WebDriverWait getWait() {
		return wait;
	}

	private enum Browser {
		CHROME, FIREFOX
	}

	@BeforeClass
	public static void setUp() throws Exception {
		chooseBrowser(Browser.CHROME);
		navigator = new NavigateTest();
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
				String pathToDriverChromeWin = "drivers/driversWin/chromedriver.exe";
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
}
