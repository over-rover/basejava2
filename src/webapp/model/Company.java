package webapp.model;

import webapp.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company implements Serializable {
    public Link homePage;
    private List<Period> periods = new ArrayList<>();

    public Company() {
        this("");
    }

    public Company(String name) {
        this(name, "");
    }

    public Company(String name, String url) {
        homePage = new Link(name, url);
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
        StringBuilder s = new StringBuilder(homePage.toString() + "\n");
        for (Period value : periods) {
            s.append(value.toString());
        }

        return s.toString();
    }

    public static class Period implements Serializable {
        private static final long serialVersionUID = 1L;
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

        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(int year, Month month) {
            this.startDate = DateUtil.of(year, month);
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(int year, Month month) {
            this.endDate = DateUtil.of(year, month);
        }

        public void setEndDate(LocalDate now) {
            this.endDate = now;
        }

        @Override
        public boolean equals(Object searchKey) {
            if (this == searchKey) return true;
            if (searchKey == null || getClass() != searchKey.getClass()) return false;

            Period period = (Period) searchKey;

            if (!Objects.equals(title, period.title)) return false;
            if (!Objects.equals(description, period.description))
                return false;
            if (!Objects.equals(startDate, period.startDate)) return false;
            return Objects.equals(endDate, period.endDate);
        }

        @Override
        public int hashCode() {
            int result = title != null ? title.hashCode() : 0;
            result = 31 * result + (description != null ? description.hashCode() : 0);
            result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
            result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Период " + startDate + " : " + endDate + "\n" +
                    title + "\n" + description + "\n";
        }
    }
}