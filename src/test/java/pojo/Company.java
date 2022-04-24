package pojo;

import java.util.Objects;

public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(getName(), company.getName()) && Objects.equals(getCatchPhrase(), company.getCatchPhrase()) && Objects.equals(getBs(), company.getBs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCatchPhrase(), getBs());
    }

    public String getName() {
        return name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
