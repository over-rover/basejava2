package maintest;

import webapp.model.*;
import webapp.util.DateUtil;

import java.time.Month;

public class MainResumeTestData {

    public static void main(String[] args) {
        //fillContent("ФИО1","uuid1").printToConsole();
        //autoFillContent("ФИО1", "uuid1").printToConsole();
        //autoFillContent("ФИО2", "uuid2").printToConsole();
    }

    /*Автозаполнение резюме для проведения тестов.
     * Все объекты идентифицируются по fullName-uuid и порядковому номеру объекта, если их несколько
     * Количество достижений и квалификаций определяется случайным образом от 1 до 3 включительно
     * Количество компаний-работодателей - 2
     * Количество компаний-учебных заведений - 2
     * Количество периодов (а также должностей и должностных обязанностей)
     * определяется случайным образом от 1 до 3 включительно
     * Вопрос по заполнению startDate и endDate требует пояснений*/
    public static Resume autoFillContent(String fullName, String uuid) {
        String user = fullName + " " + uuid;

        Resume resume = new Resume(fullName, uuid);
        resume.addContact(ContactType.PHONE, user + " +7(xxx)");
        resume.addContact(ContactType.EMAIL, user + " email");
        resume.addContact(ContactType.SKYPE, user + " skype");
        resume.addContact(ContactType.LINKEDIN, user + " linkedin");
        resume.addContact(ContactType.GITHUB, user + " github");
        resume.addContact(ContactType.STACKOVERFLOW, user + " stackoverflow");
        resume.addContact(ContactType.HOMEPAGE, user + " homepage");

        resume.addSection(SectionType.OBJECTIVE, new TextSection(user + " objective"));
        resume.addSection(SectionType.PERSONAL, new TextSection(user + " personal"));

        ListSection listSection = new ListSection();
        for (int a = 1; a <= (int) (Math.random() * 3 + 1); a++) {
            listSection.addDescription(user + " имеет достижение " + a);
        }
        resume.addSection(SectionType.ACHIEVMENT, listSection);

        listSection = new ListSection();
        for (int q = 1; q <= (int) (Math.random() * 3 + 1); q++) {
            listSection.addDescription(user + " имеет квалификацию " + q);
        }
        resume.addSection(SectionType.QUALIFICATIONS, listSection);

        CompanySection eduCompanySection = new CompanySection();
        CompanySection expCompanySection = new CompanySection();
        for (int c = 1; c <= 4; c++) {
            Company company = new Company();
            company.homePage.setName(user + " Company-" + c + " name");
            company.homePage.setUrl(user + " www.company-" + c + ".ru");
            for (int p = 1; p <= (int) (Math.random() * 3 + 1); p++) {
                Company.Period period = new Company.Period();
                period.setTitle(user + " Должность-" + p);
                period.setDescription(user + " Должностные обязанности - " + p);
                period.setStartDate(2000, Month.JANUARY);
                period.setEndDate(2000, Month.DECEMBER);
                company.addPeriod(period);
            }

            if (c <= 2) {
                expCompanySection.add(company);
                resume.addSection(SectionType.EXPERIENCE, expCompanySection);
            } else {
                eduCompanySection.add(company);
                resume.addSection(SectionType.EDUCATION, eduCompanySection);
            }
        }

        return resume;
    }

    public static Resume fillContent(String fullName, String uuid) {
        Resume resume = new Resume(fullName, uuid);
        resume.addContact(ContactType.PHONE, "+70000000000");
        resume.addContact(ContactType.EMAIL, "my_email@mail.ru");
        resume.addContact(ContactType.SKYPE, "skype");
        resume.addContact(ContactType.LINKEDIN, "linkedin");
        resume.addContact(ContactType.GITHUB, "github");
        resume.addContact(ContactType.STACKOVERFLOW, "stackoverflow");
        resume.addContact(ContactType.HOMEPAGE, "homepage");
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Заполняем раздел \"Позиция\""));
        resume.addSection(SectionType.PERSONAL, new TextSection("Здесь пишем о личных качествах"));
        ListSection listSection = new ListSection();
        listSection.addDescription("Имею достижение 1");
        listSection.addDescription("Имею достижение 2");
        listSection.addDescription("Имею достижение 3");
        resume.addSection(SectionType.ACHIEVMENT, listSection);

        listSection = new ListSection();
        listSection.addDescription("Имею квалификацию 1");
        listSection.addDescription("Имею квалификацию 2");
        listSection.addDescription("Имею квалификацию 3");
        listSection.addDescription("Имею квалификацию 4");
        resume.addSection(SectionType.QUALIFICATIONS, listSection);

        CompanySection companyExSection = new CompanySection();
        Company company = new Company();
        company.homePage.setName("Компания-1");
        company.homePage.setUrl("https://company-1");

        Company.Period period = new Company.Period();
        period.setTitle("В период1 занимал Должность1");
        period.setDescription("Здесь указываем должностные обязанности в компании1 в период1");
        period.setStartDate(2004, Month.JANUARY);
        period.setEndDate(2004, Month.DECEMBER);
        company.addPeriod(period);

        period = new Company.Period();
        period.setTitle("В период2 занимал Должность2");
        period.setDescription("Здесь указываем должностные обязанности в компании1 в период2");
        period.setStartDate(2005, Month.JANUARY);
        period.setEndDate(2005, Month.DECEMBER);
        company.addPeriod(period);

        companyExSection.add(company);
        resume.addSection(SectionType.EXPERIENCE, companyExSection);

        company = new Company();
        company.homePage.setName("Компания-2");
        company.homePage.setUrl("https://company-2");

        period = new Company.Period();
        period.setTitle("Занимаю Должность...");
        period.setDescription("Здесь указываем должностные обязанности в компании2");
        period.setStartDate(2006, Month.JANUARY);
        period.setEndDate(DateUtil.NOW);
        company.addPeriod(period);

        companyExSection.add(company);

        resume.addSection(SectionType.EXPERIENCE, companyExSection);


        CompanySection companyEdSection = new CompanySection();
        company = new Company();
        company.homePage.setName("ВУЗ-1");
        company.homePage.setUrl("https://ed1");
        period = new Company.Period();
        period.setTitle("Студент");
        period.setDescription("Проходил следующие дисциплины: основы студентуры");
        period.setStartDate(2000, Month.JANUARY);
        period.setEndDate(2000, Month.DECEMBER);
        company.addPeriod(period);

        companyEdSection.add(company);
        resume.addSection(SectionType.EDUCATION, companyEdSection);

        period = new Company.Period();
        period.setTitle("Магистр");
        period.setDescription("Проходил следующие дисциплины: основы магистратуры");
        period.setStartDate(2001, Month.JANUARY);
        period.setEndDate(2001, Month.DECEMBER);
        company.addPeriod(period);


        period = new Company.Period();
        period.setTitle("Аспирант");
        period.setDescription("Проходил следующие дисциплины: основы аспирантуры");
        period.setStartDate(2002, Month.JANUARY);
        period.setEndDate(2002, Month.DECEMBER);
        company.addPeriod(period);

        company = new Company();
        company.homePage.setName("ВУЗ-2");
        company.homePage.setUrl("https://ed2");
        period = new Company.Period();
        period.setTitle("Повышение квалификации");
        period.setDescription("Проходил следующие дисциплины: основы повышения квалификации");
        period.setStartDate(2003, Month.JANUARY);
        period.setEndDate(2003, Month.DECEMBER);
        company.addPeriod(period);

        companyEdSection.add(company);
        resume.addSection(SectionType.EDUCATION, companyEdSection);

        return resume;
    }
}