package webapp.storage.serializer;

import webapp.model.Company;
import webapp.model.CompanySection;
import webapp.model.ContactType;
import webapp.model.ListSection;
import webapp.model.Resume;
import webapp.model.Section;
import webapp.model.SectionType;
import webapp.model.TextSection;
import webapp.util.DateUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Month;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(fullName, uuid);
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                ContactType contactType = ContactType.valueOf(dis.readUTF());
                String contact = dis.readUTF();
                resume.addContact(contactType, contact);
            }

            int numOfSections = dis.readInt();
            for (int section = 0; section < numOfSections; section++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());

                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        TextSection textSection = new TextSection(dis.readUTF());
                        resume.addSection(sectionType, textSection);
                        break;
                    case ACHIEVMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = new ListSection();
                        int numOfLists = dis.readInt();
                        for (int list = 0; list < numOfLists; list++) {
                            listSection.addDescription(dis.readUTF());
                        }
                        resume.addSection(sectionType, listSection);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        CompanySection companySection = new CompanySection();
                        Company company;
                        int numOfCompanies = dis.readInt();
                        for (int companyNumber = 0; companyNumber < numOfCompanies; companyNumber++) {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            url = getAsNullIfStringValue(url);
                            company = new Company(name, url);
                            Company.Period period;
                            int numOfPeriods = dis.readInt();
                            for (int periodNumber = 0; periodNumber < numOfPeriods; periodNumber++) {
                                period = new Company.Period();
                                readPeriod(dis, period);
                                company.addPeriod(period);
                            }
                            companySection.add(company);
                            resume.addSection(sectionType, companySection);
                        }
                }
            }
            return resume;
        }
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            dos.writeInt(r.getContacts().size());
            for (Map.Entry<ContactType, String> contactEntry : r.getContacts().entrySet()) {
                dos.writeUTF(contactEntry.getKey().name());
                dos.writeUTF(contactEntry.getValue());
            }

            dos.writeInt(r.getSections().size());
            for (Map.Entry<SectionType, Section> sectionsEntry : r.getSections().entrySet()) {
                String sectionType = sectionsEntry.getKey().name();
                dos.writeUTF(sectionType);

                switch (sectionsEntry.getKey()) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(sectionsEntry.getValue().toString());
                        break;
                    case ACHIEVMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = (ListSection) sectionsEntry.getValue();
                        dos.writeInt(listSection.getDescriptions().size());
                        for (String description : listSection.getDescriptions()) {
                            dos.writeUTF(description);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        CompanySection companySection = (CompanySection) sectionsEntry.getValue();
                        dos.writeInt(companySection.getCompanies().size());
                        for (Company company : companySection.getCompanies()) {
                            dos.writeUTF(company.getHomePage().getName());
                            String url = getAsStringIfNull(company.getHomePage().getUrl());
                            dos.writeUTF(url);
                            dos.writeInt(company.getPeriods().size());
                            for (Company.Period period : company.getPeriods()) {
                                writePeriod(dos, period);
                            }
                        }
                }
            }
        }
    }

    private String getAsStringIfNull(String value) {
        return (value == null) ? "null" : value;
    }

    private String getAsNullIfStringValue(String value) {
        return (value.equals("null")) ? null : value;
    }

    private void writePeriod(DataOutputStream dos, Company.Period period) throws IOException {
        dos.writeInt(period.getStartDate().getYear());
        dos.writeInt(period.getStartDate().getMonthValue());
        dos.writeInt(period.getEndDate().getYear());
        dos.writeInt(period.getEndDate().getMonthValue());
        dos.writeUTF(period.getTitle());
        String description = getAsStringIfNull(period.getDescription());
        dos.writeUTF(description);
    }

    private void readPeriod(DataInputStream dis, Company.Period period) throws IOException {
        int year = dis.readInt();
        int month = dis.readInt();
        period.setStartDate(DateUtil.of(year, Month.of(month)));
        year = dis.readInt();
        month = dis.readInt();
        period.setEndDate(DateUtil.of(year, Month.of(month)));
        period.setTitle(dis.readUTF());
        String description = dis.readUTF();
        period.setDescription(getAsNullIfStringValue(description));
    }
}