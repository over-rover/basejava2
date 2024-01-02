package webapp.model;

public class TextSection extends Section {
    private String description;

    public TextSection(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
