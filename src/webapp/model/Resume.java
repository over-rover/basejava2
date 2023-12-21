package webapp.model;

import java.util.UUID;

public class Resume implements Comparable<Resume> {

    private final String uuid;
    private String fullName = "Anonimus";

    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume r = (Resume) o;
        if (!fullName.equals(r.fullName)) {
            return false;
        } else return uuid.equals(r.uuid);
    }

    @Override
    public int hashCode() {
        return ((fullName + uuid).hashCode());
    }

    @Override
    public String toString() {
        return fullName + " " + uuid;
    }

    @Override
    public int compareTo(Resume r) {
        return (fullName + uuid).compareTo(r.fullName + r.uuid);
    }
}