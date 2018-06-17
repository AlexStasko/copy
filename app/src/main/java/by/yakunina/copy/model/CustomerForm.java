package by.yakunina.copy.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CustomerForm {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;

    public CustomerForm() {
        this.username = null;
        this.password = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.address = null;
        this.phoneNumber = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("username", username)
                .append("password", password)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("email", email)
                .append("address", address)
                .append("phoneNumber", phoneNumber)
                .toString();
    }
}
