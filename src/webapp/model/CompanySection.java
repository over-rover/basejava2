package webapp.model;

import java.util.ArrayList;
import java.util.List;

public class CompanySection extends Section {
    private final List<Company> companies = new ArrayList<>();

    public List<Company> getCompanies() {
        return companies;
    }

    public void add(Company company) {
        companies.add(company);
    }



    public void delete(Company company) {
        companies.remove(company);
    }

    @Override
    public boolean equals(Object searchKey) {
        if (this == searchKey) return true;
        if (searchKey == null || getClass() != searchKey.getClass()) return false;

        CompanySection that = (CompanySection) searchKey;

        return companies.equals(that.companies);
    }

    @Override
    public int hashCode() {
        return companies.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Company company : companies) {
            stringBuilder.append(company);
        }
        return stringBuilder.toString();
    }
}