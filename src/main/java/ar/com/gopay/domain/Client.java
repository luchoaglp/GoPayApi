package ar.com.gopay.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clients")
public class Client extends User {

    @NotBlank
    @Size(min = 6, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 6, max = 50)
    private String lastName;

    @NotBlank
    @Size(min = 7, max = 8)
    private String dni;

    public Client() { }

    public Client(String email, String password,
                  String firstName, String lastName, String dni) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dni='" + dni + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

}
