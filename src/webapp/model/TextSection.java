package webapp.model;

public class TextSection extends Section {
    private static final long serialVersionUID = 1L;
    private String description;

    public TextSection() {
    }

    public TextSection(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object searchKey) {
        if (this == searchKey) return true;
        if (searchKey == null || getClass() != searchKey.getClass()) return false;

        TextSection that = (TextSection) searchKey;

        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
