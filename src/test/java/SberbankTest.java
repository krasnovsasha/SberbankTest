/*
Here is the description
	-first- choose browser you wish to use, for that
		go to the class BaseSettingsTest -> find setUp() method ->
			-> find chooseBrowser() method -> choose your browser from list
	-second- run sberbankTest() method
 */

import org.junit.Test;

public class SberbankTest extends BaseSettingsTest {
	@Test
	public void sberbankTest() {
		//Перейти на страницу http://www.sberbank.ru/ru/person
		navigator.goToPage(navigator.urlSBMainPage);
		navigator.printTitleAndURL();
		//Нажать на – Страхование
		navigator.clickByXPath(navigator.insuranceMainPageXPath);
		navigator.printTitleAndURL();
		//Выбрать – Путешествие и покупки
		navigator.clickByXPath(navigator.insuranceForTravellersXPath);
		navigator.printTitleAndURL();
	}
}
