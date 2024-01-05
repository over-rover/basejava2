package webapp.model;

import java.time.LocalDate;

public class Period {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate.toString();
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    public String getEndDate() {
        return endDate.toString();
    }

    public void setEndDate(String endDate) {
        if (endDate.equalsIgnoreCase("По настоящее время"))
            this.endDate = LocalDate.now();
        else
            this.endDate = LocalDate.parse(endDate);
    }

    @Override
    public String toString() {
        return "Период " + startDate + " : " + endDate + "\n" +
                title + "\n" + description + "\n";
    }
}
