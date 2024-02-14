package webapp.storage.serializer;

import webapp.model.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
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
                            company = new Company(dis.readUTF(), dis.readUTF());
                            Company.Period period;
                            int numOfPeriods = dis.readInt();
                            for (int periodNumber = 0; periodNumber < numOfPeriods; periodNumber++) {
                                period = new Company.Period();
                                String startPeriod = dis.readUTF();
                                period.setStartDate(LocalDate.parse(startPeriod));
                                String endPeriod = dis.readUTF();
                                period.setEndDate(LocalDate.parse(endPeriod));
                                period.setTitle(dis.readUTF());
                                period.setDescription(dis.readUTF());
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
                            dos.writeUTF(company.getHomePage().getUrl());
                            dos.writeInt(company.getPeriods().size());
                            for (Company.Period period : company.getPeriods()) {
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getDescription());
                            }
                        }
                }
            }
        }
    }
}