package webapp.model;

import java.util.UUID;

public class Resume implements Comparable<Resume> {

    private final String fullName;
    private final String uuid;

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
        return uuid.compareTo(r.uuid);
    }
}