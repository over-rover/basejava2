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

        CompanySection company = new CompanySection();
        company.setName("Компания-1");
        company.setWebsite("https://company-1");

        Period period = new Period();
        period.setTitle("В период1 занимал Должность1");
        period.setDescription("Здесь указываем должностные обязанности в компании1 в период1");
        period.setStartDate("Начало работы: 00.00.0004");
        period.setEndDate("Конец работы: 00.00.0005");
        company.addPeriod(period);
        //period = company.getPeriods().get(0);
        //updatePeriodField
        //company.getPeriods().set(0, period);

        period = new Period();
        period.setTitle("В период2 занимал Должность2");
        period.setDescription("Здесь указываем должностные обязанности в компании1 в период2");
        period.setStartDate("Начало работы: 00.00.0005");
        period.setEndDate("Конец работы: 00.00.0006");
        company.addPeriod(period);

        resume.addSection(SectionType.EXPERIENCE, company);

        company = new CompanySection();
        company.setName("Компания-2");
        company.setWebsite("https://company-2");

        period = new Period();
        period.setTitle("Занимаю Должность...");
        period.setDescription("Здесь указываем должностные обязанности в компании2");
        period.setStartDate("Начало работы: 00.00.0006");
        period.setEndDate("Конец работы: настоящее время");
        company.addPeriod(period);

        resume.addSection(SectionType.EXPERIENCE, company);


        company = new CompanySection();
        company.setName("ВУЗ-1");
        company.setWebsite("https://ed1");
        period = new Period();
        period.setTitle("Студент");
        period.setDescription("Проходил следующие дисциплины: основы студентуры");
        period.setStartDate("Начало: 00.00.0000");
        period.setEndDate("Конец: 00.00.0001");
        company.addPeriod(period);

        resume.addSection(SectionType.EDUCATION, company);

        period = new Period();
        period.setTitle("Магистр");
        period.setDescription("Проходил следующие дисциплины: основы магистратуры");
        period.setStartDate("Начало: 00.00.0001");
        period.setEndDate("Конец: 00.00.0002");
        company.addPeriod(period);


        period = new Period();
        period.setTitle("Аспирант");
        period.setDescription("Проходил следующие дисциплины: основы аспирантуры");
        period.setStartDate("Начало: 00.00.0002");
        period.setEndDate("Конец: 00.00.0003");
        company.addPeriod(period);

        company = new CompanySection();
        company.setName("ВУЗ-2");
        company.setWebsite("https://ed2");
        period = new Period();
        period.setTitle("Повышение квалификации");
        period.setDescription("Проходил следующие дисциплины: основы повышения квалификации");
        period.setStartDate("Начало: 00.00.0003");
        period.setEndDate("Конец: 00.00.0004");
        company.addPeriod(period);
        resume.addSection(SectionType.EDUCATION, company);

        resume.printToConsole();
    }
}