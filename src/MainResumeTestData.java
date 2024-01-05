import webapp.model.*;

public class MainResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("ФИО1");
        resume.addContact(ContactType.PHONE, "+70000000000");
        resume.addContact(ContactType.EMAIL, "my_email@mail.ru");
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

        CompanySection companySection = new CompanySection();
        Company company = new Company();
        company.setName("Компания-1");
        company.setWebsite("https://company-1");

        Period period = new Period();
        period.setTitle("В период1 занимал Должность1");
        period.setDescription("Здесь указываем должностные обязанности в компании1 в период1");
        period.setStartDate("2004-01-01");
        period.setEndDate("2004-12-31");
        company.addPeriod(period);

        period = new Period();
        period.setTitle("В период2 занимал Должность2");
        period.setDescription("Здесь указываем должностные обязанности в компании1 в период2");
        period.setStartDate("2005-01-01");
        period.setEndDate("2005-12-31");
        company.addPeriod(period);

        companySection.add(company);
        resume.addSection(SectionType.EXPERIENCE, companySection);

        company = new Company();
        company.setName("Компания-2");
        company.setWebsite("https://company-2");

        period = new Period();
        period.setTitle("Занимаю Должность...");
        period.setDescription("Здесь указываем должностные обязанности в компании2");
        period.setStartDate("2006-01-01");
        period.setEndDate("По настоящее время");
        company.addPeriod(period);

        companySection.add(company);

        resume.addSection(SectionType.EXPERIENCE, companySection);


        company = new Company();
        company.setName("ВУЗ-1");
        company.setWebsite("https://ed1");
        period = new Period();
        period.setTitle("Студент");
        period.setDescription("Проходил следующие дисциплины: основы студентуры");
        period.setStartDate("2000-01-01");
        period.setEndDate("2000-12-31");
        company.addPeriod(period);

        companySection.add(company);
        resume.addSection(SectionType.EDUCATION, companySection);

        period = new Period();
        period.setTitle("Магистр");
        period.setDescription("Проходил следующие дисциплины: основы магистратуры");
        period.setStartDate("2001-01-01");
        period.setEndDate("2001-12-31");
        company.addPeriod(period);


        period = new Period();
        period.setTitle("Аспирант");
        period.setDescription("Проходил следующие дисциплины: основы аспирантуры");
        period.setStartDate("2002-01-01");
        period.setEndDate("2002-12-31");
        company.addPeriod(period);

        company = new Company();
        company.setName("ВУЗ-2");
        company.setWebsite("https://ed2");
        period = new Period();
        period.setTitle("Повышение квалификации");
        period.setDescription("Проходил следующие дисциплины: основы повышения квалификации");
        period.setStartDate("2003-01-01");
        period.setEndDate("2003-12-31");
        company.addPeriod(period);

        companySection.add(company);
        resume.addSection(SectionType.EDUCATION, companySection);

        resume.printToConsole();
    }
}