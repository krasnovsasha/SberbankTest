import org.junit.Test;

public class SberbankTest extends BaseSettingsTest {
	@Test
	public void sberbankTest() {
		//Перейти на страницу http://www.sberbank.ru/ru/person
		goToPage(urlSBMainPage);
		//Нажать на – Страхование
		clickByXPath(insuranceMainPageXPath);
		//Выбрать – Путешествие и покупки
		clickByJS(insuranceForTravellersXPath);
	}
}
