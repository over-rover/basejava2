package webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;
    private String fullName;
    private String uuid;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(fullName, UUID.randomUUID().toString());
    }

    public Resume(String fullName, String uuid) {
        this.fullName = fullName;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public void addContact(ContactType contactType, String contact) {
        contacts.put(contactType, contact);
    }

    public void addSection(SectionType sectionType, Section section) {
        sections.put(sectionType, section);
    }

    public void printToConsole() {
        System.out.println(fullName);
        for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
            System.out.println(entry.getKey() + "\n" + entry.getValue());
        }
    }

    @Override
    public boolean equals(Object searchKey) {
        if (this == searchKey) return true;
        if (searchKey == null || getClass() != searchKey.getClass()) return false;
        Resume resume = (Resume) searchKey;
        return Objects.equals(fullName, resume.fullName) &&
                Objects.equals(uuid, resume.uuid) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, uuid, contacts, sections);
    }

    @Override
    public String toString() {
        return fullName + " " + uuid;
    }

    @Override
    public int compareTo(Resume r) {
        return uuid.compareTo(r.uuid);
    }
}