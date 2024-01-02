package webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section {
    private final List<String> descriptions = new ArrayList<>();

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
}
