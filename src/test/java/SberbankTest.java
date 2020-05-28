/*
 for run please use commandline
 1) mvn test -Dbrowser=chrome
 2) mvn clean test -Dbrowser=firefox
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SberbankTest extends BaseSettingsTest {
	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{{"Ivanov", "Ivan", "10.10.1995", "Сидоров", "Петр", "Васильевич", "10.10.1995", "1111", "222222", "10.10.2015", "Выдан государственным органом "},
				{"Ivanov", "Ivan", "10.10.1995", "Сидоров", "Петр", "Васильевич", "10.10.1995", "1111", "222222", "10.10.2015", "Выдан государственным органом "},
				{"Ivanov", "Ivan", "10.10.1995", "Сидоров", "Петр", "Васильевич", "10.10.1995", "1111", "222222", "10.10.2015", "Выдан государственным органом "}});
	}
	@Parameterized.Parameter
	public String surname;
	@Parameterized.Parameter(1)
	public String name;
	@Parameterized.Parameter(2)
	public String birthDate;
	@Parameterized.Parameter(3)
	public String personLastName;
	@Parameterized.Parameter(4)
	public String personFirstName;
	@Parameterized.Parameter(5)
	public String personMiddleName;
	@Parameterized.Parameter(6)
	public String personBirthDate;
	@Parameterized.Parameter(7)
	public String passportSeries;
	@Parameterized.Parameter(8)
	public String passportNumber;
	@Parameterized.Parameter(9)
	public String dateOfPassport;
	@Parameterized.Parameter(10)
	public String documentIssue;

	@Test
	public void sberbankTest() {
		//Перейти на страницу http://www.sberbank.ru/ru/person
		navigator.goToPage(navigator.urlSBMainPage);
		//Нажать на – Страхование
		navigator.clickByXPath(navigator.insuranceMainPageXPath);
		//Выбрать – Путешествие и покупки
		navigator.clickByXPath(navigator.insuranceForTravellersXPath);
		//Проверить наличие на странице заголовка – Страхование путешественников
		navigator.checkTheText(navigator.textAboutInsuranceOfTraveller, navigator.expectedTextInsurancePage);
		//Нажать на – Оформить Онлайн
		navigator.waitUntilPresent(navigator.buttonArrangeOnlineXPath);
		navigator.scrollToElement(navigator.buttonArrangeOnlineXPath);
		navigator.clickByXPath(navigator.buttonArrangeOnlineXPath);
		//На вкладке – Выбор полиса выбрать сумму страховой защиты – Минимальная
		navigator.waitUntilPresent(navigator.minimalInsuranceXPath); //Часто падает с браузером FireFox,поэтому столько ожиданий
		navigator.waitUntilVisible(navigator.minimalInsuranceXPath);
		navigator.waitUntilClickable(navigator.minimalInsuranceXPath);
		navigator.clickByXPath(navigator.minimalInsuranceXPath);
		//Нажать Оформить
		navigator.waitUntilPresent(navigator.buttonCheckout);
		navigator.clickByXPath(navigator.buttonCheckout);
		//На вкладке Оформить заполнить поля: Фамилию и Имя, Дату рождения застрахованных
		//Данные страхователя: Фамилия, Имя, Отчество, Дата рождения, Пол, Паспортные данные
		//Контактные данные не заполняем
		navigator.waitUntilClickable(navigator.inputSurname);
		navigator.clickByID("surname_vzr_ins_0");
		navigator.sendText(navigator.inputSurname, surname);
		navigator.clickByID("name_vzr_ins_0");
		navigator.sendText(navigator.inputName, name);
		navigator.clickByID("birthDate_vzr_ins_0");
		navigator.sendText(navigator.inputBirthDate, birthDate);
		navigator.clickByID("person_lastName");
		navigator.sendText(navigator.inputPersonLastName, personLastName);
		navigator.clickByID("person_firstName");
		navigator.sendText(navigator.inputPersonFirstName, personFirstName);
		navigator.clickByID("person_middleName");
		navigator.sendText(navigator.inputPersonMiddleName, personMiddleName);
		navigator.clickByID("person_birthDate");
		navigator.sendText(navigator.inputPersonBirthDate, personBirthDate);
		navigator.clickByXPath(navigator.inputSexMen);
		navigator.clickByID("passportSeries");
		navigator.sendText(navigator.passportSeries, passportSeries);
		navigator.clickByID("passportNumber");
		navigator.sendText(navigator.passportNumber, passportNumber);
		navigator.clickByID("documentDate");
		navigator.sendText(navigator.dateOfPassport, dateOfPassport);
		navigator.clickByID("documentIssue");
		navigator.sendText(navigator.documentIssue, documentIssue);
		//Проверить, что все поля заполнены правильно
		navigator.checkTheValue(navigator.inputSurname, surname);
		navigator.checkTheValue(navigator.inputName, name);
		navigator.checkTheValue(navigator.inputBirthDate, birthDate);
		navigator.checkTheValue(navigator.inputPersonLastName, personLastName);
		navigator.checkTheValue(navigator.inputPersonFirstName, personFirstName);
		navigator.checkTheValue(navigator.inputPersonMiddleName, personMiddleName);
		navigator.checkTheValue(navigator.inputPersonBirthDate, personBirthDate);
		navigator.checkTheValue(navigator.passportSeries, passportSeries);
		navigator.checkTheValue(navigator.passportNumber, passportNumber);
		navigator.checkTheValue(navigator.dateOfPassport, dateOfPassport);
		navigator.checkTheValue(navigator.documentIssue, documentIssue);
		//Нажать продолжить
		navigator.submit(navigator.buttonSubmit);
		//Проверить, что появилось сообщение - Заполнены не все обязательные поля
		navigator.checkTheError("Не показано сообщение о незаполненных полях", navigator.alertMessage);
	}
}