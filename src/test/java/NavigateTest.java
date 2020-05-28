import org.hamcrest.core.Is;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigateTest extends BaseSettingsTest {
	private static WebElement element;
	private static JavascriptExecutor executor;
	private static Actions actions;
	protected String urlSBMainPage = "http://www.sberbank.ru/ru/person";
	protected String insuranceMainPageXPath = "//span[text()='Страхование']";
	protected String insuranceForTravellersXPath = "//a[@class='lg-menu__sub-link' and text()='Страхование путешественников']";
	protected String textAboutInsuranceOfTraveller = "//div[@class='kit-col_xs_12 kit-col_md_0 kit-col_lg_6 kit-col_xs-bottom_20 kit-col_lg-bottom_10 kit-col_xs-top_20 kit-col_lg-top_40']//h2";
	protected String expectedTextInsurancePage = "Страхование путешественников";
	protected String buttonArrangeOnlineXPath = "//b[text()='Оформить онлайн']/parent::a";
	protected String minimalInsuranceXPath = "//h3[text()='Минимальная']/parent::div";
	protected String buttonCheckout = "//button[text()='Оформить']";
	protected String inputSurname = "//input[@id='surname_vzr_ins_0']";
	protected String inputName = "//input[@id='name_vzr_ins_0']";
	protected String inputBirthDate = "//input[@id='birthDate_vzr_ins_0']";
	protected String inputPersonLastName = "//input[@id='person_lastName']";
	protected String inputPersonFirstName = "//input[@id='person_firstName']";
	protected String inputPersonMiddleName = "//input[@id='person_middleName']";
	protected String inputPersonBirthDate = "//input[@id='person_birthDate']";
	protected String inputSexMen = "//label[text()='Мужской']";
	protected String passportSeries = "//input[@id='passportSeries']";
	protected String passportNumber = "//input[@id='passportNumber']";
	protected String dateOfPassport = "//input[@id='documentDate']";
	protected String documentIssue = "//input[@id='documentIssue']";
	protected String buttonSubmit = "//button[@type='submit' and contains(text(),'Продолжить')]";
	protected String alertMessage = "//div[@role='alert-form']";

	void printTitleAndURL() {
		System.out.println(getDriver().getTitle() + "\n" + getDriver().getCurrentUrl());
	}
	void goToPage(String page) {
		getDriver().get(page);
	}

	void clickByXPath(String XPath) {
		try {
			getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(XPath))).click();
		} catch (ElementClickInterceptedException e) {
			System.out.println("Клик по элементу " + getDriver() + " через findElement() не удался, попытка через JSExecutor");
			clickByJS(XPath);
		} catch (TimeoutException e) {
			System.out.println("Page didn't load within 15 seconds");
		}
	}

	void clickByID(String ID) {
		getDriver().findElement(By.id(ID)).click();
	}

	void waitUntilClickable(String XPath) {
		WebElement element = getDriver().findElement(By.xpath(XPath));
		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	void waitUntilVisible(String XPath) {
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPath)));
	}

	void waitUntilPresent(String XPath) {
		getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
	}

	void clickByJS(String XPath) {
		element = getDriver().findElement(By.xpath(XPath));
		executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", element);
	}

	void goForward() {
		getDriver().navigate().forward();
	}

	void goBack() {
		getDriver().navigate().back();
	}

	void refreshPage() {
		getDriver().navigate().refresh();
	}

	void checkTheText(String XPath, String expected) {
		String result = getDriver().findElement(By.xpath(XPath)).getText();
		Assert.assertThat("Текст на странице не соответствует ожидаемому", result, Is.is(expected));
	}

	void checkTheValue(String XPath, String expected) {
		String result = getDriver().findElement(By.xpath(XPath)).getAttribute("value");
		Assert.assertThat("Текст на странице не соответствует ожидаемому", result, Is.is(expected));
	}

	void moveToElement(WebElement element) {
		actions = new Actions(getDriver());
		actions.moveToElement(element).build().perform();
	}

	void scrollToElement(String XPath) {
		element = getDriver().findElement(By.xpath(XPath));
		executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].scrollIntoView();", element);
	}

	void sendText(String XPath, String text) {
		getDriver().findElement(By.xpath(XPath)).sendKeys(text);
	}

	void submit(String XPath) {
		getDriver().findElement(By.xpath(XPath)).submit();
	}

	void checkTheError(String message, String XPath) {
		Boolean el = getDriver().findElement(By.xpath(XPath)).isDisplayed();
		Assert.assertTrue(message, el);
	}
}
