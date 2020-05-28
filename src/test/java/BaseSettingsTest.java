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
	private final static String pathToDriverFirefoxLin = "drivers/driversLin/geckodriver";
	private final static String pathToDriverChromeMac = "drivers/driversMac/chromedriver";
	private final static String pathToDriverFirefoxMac = "drivers/driversMac/geckodriver";
	private final static String pathToDriverChromeWin = "drivers/driversWin/chromedriver.exe";
	private final static String pathToDriverFirefoxWin = "drivers/driversWin/geckodriver.exe";

	public static WebDriver getDriver() {
		return driver;
	}

	public static WebDriverWait getWait() {
		return wait;
	}

	@BeforeClass
	public static void setUp() throws Exception {
		setUpBrowser(System.getProperty("browser", "chrome"));
		navigator = new NavigateTest();
		wait = new WebDriverWait(driver, 20);
		driver.manage().window().maximize();
//		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}

	private static void setUpBrowser(String browserName) {
		OS = System.getProperty("os.name").toLowerCase();
		String[] osName = OS.split("\\s");
		switch (osName[0]) {
			case "linux":
				switch (browserName) {
					case "chrome":
						System.setProperty("webdriver.chrome.driver", pathToDriverChromeLin);
						driver = new ChromeDriver();
						break;
					case "firefox":
						System.setProperty("webdriver.gecko.driver", pathToDriverFirefoxLin);
						driver = new FirefoxDriver();
						break;
				}
				break;
			case "windows":
				String pathToDriverChromeWin = "drivers/driversWin/chromedriver.exe";
				switch (browserName) {
					case "chrome":
						System.setProperty("webdriver.chrome.driver", pathToDriverChromeWin);
						driver = new ChromeDriver();
						break;
					case "firefox":
						System.setProperty("webdriver.gecko.driver", pathToDriverFirefoxWin);
						driver = new FirefoxDriver();
						break;
				}
				break;
			case "mac":
				switch (browserName) {
					case "chrome":
						System.setProperty("webdriver.chrome.driver", pathToDriverChromeMac);
						driver = new ChromeDriver();
						break;
					case "firefox":
						System.setProperty("webdriver.gecko.driver", pathToDriverFirefoxMac);
						driver = new FirefoxDriver();
						break;
				}
				break;
		}
	}
}
