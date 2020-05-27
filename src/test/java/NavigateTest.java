import org.hamcrest.core.Is;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigateTest extends BaseSettingsTest {
	private static WebElement element;
	private static JavascriptExecutor executor;
	protected String urlSBMainPage = "http://www.sberbank.ru/ru/person";
	protected String insuranceMainPageXPath = "//span[text()='Страхование']";
	protected String insuranceForTravellersXPath = "//a[@class='lg-menu__sub-link' and text()='Страхование путешественников']";
	protected String textAboutInsuranceOfTraveller ="//div[@class=\"kit-col_xs_12 kit-col_md_0 kit-col_lg_6 kit-col_xs-bottom_20 kit-col_lg-bottom_10 kit-col_xs-top_20 kit-col_lg-top_40\"]//h2";

	public NavigateTest() {
		this.urlSBMainPage = urlSBMainPage;
		this.insuranceMainPageXPath = insuranceMainPageXPath;
		this.insuranceForTravellersXPath = insuranceForTravellersXPath;
	}

	protected void printTitleAndURL() {
		System.out.println(getDriver().getTitle() + "\n" + getDriver().getCurrentUrl());
	}

	protected static void goToPage(String page) {
		getDriver().get(page);
	}

	protected void clickByXPath(String XPath) {
		try {
			getDriver().findElement(By.xpath(XPath)).click();
		} catch (ElementClickInterceptedException e) {
			System.out.println("Клик по элементу" +getDriver() + " через findElement() не удался, попытка через JSExecutor");
			clickByJS(XPath);
		}
	}

	protected void clickByID(String ID) {
		getDriver().findElement(By.id(ID)).click();
	}

	protected void waitUntilClickable(String XPath) {
		WebElement element = getDriver().findElement(By.xpath(XPath));
		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void waitUntilVisible(String XPath) {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPath)));
	}

	protected void waitUntilPresent(String XPath) {
		getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
	}

	protected void clickByJS(String XPath) {
		element = getDriver().findElement(By.xpath(XPath));
		executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", element);
	}

	protected void goForward() {
		getDriver().navigate().forward();
	}

	protected void goBack() {
		getDriver().navigate().back();
	}

	protected void refreshPage() {
		getDriver().navigate().refresh();
	}

	protected void checkTheText(String XPath) {
		String result  = getDriver().findElement(By.xpath(XPath)).getText();
		Assert.assertThat("Текст на странице не соответствует ожидаемому",result, Is.is("Страхование путешественников"));
	}
}
