package webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section {
    private static final long serialVersionUID = 1L;
    private List<String> descriptions = new ArrayList<>();

    public ListSection() {
    }

    public void addDescription(String description) {
        descriptions.add(description);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (String value : descriptions) {
            s.append(value).append("\n");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object searchKey) {
        if (this == searchKey) return true;
        if (searchKey == null || getClass() != searchKey.getClass()) return false;

        ListSection that = (ListSection) searchKey;

        return descriptions.equals(that.descriptions);
    }

    @Override
    public int hashCode() {
        return descriptions.hashCode();
    }
}
