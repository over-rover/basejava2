package webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;
    private String website;
    private List<Period> periods = new ArrayList<>();

    public Company() {
        this("");
    }

    public Company(String name) {
        this(name, "");
    }

    public Company(String name, String website) {
        this.name = name;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public void addPeriod(Period period) {
        periods.add(period);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(name + "\n" + website + "\n");
        for (Period value : periods) {
            s.append(value.toString());
        }

        return s.toString();
    }
}