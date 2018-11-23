package ar.com.gopay.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "companies")
public class Company extends User {

    public Company() { }

    public Company(String name, String username, @Email String email, String password) {
        super(name, username, email, password);
    }

    @Override
    public String toString() {
        return "Company{}";
    }
}
