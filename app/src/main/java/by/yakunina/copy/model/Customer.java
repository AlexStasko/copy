package by.yakunina.copy.model;

import by.yakunina.copy.model.support.Entity;

public class Customer extends Entity {

    private final String name;
    private final String lastName;
    private final String address;
    private final String phoneNumber;

    private Customer() {
        super(-1);
        this.name = null;
        this.lastName = null;
        this.address = null;
        this.phoneNumber = null;
    }

    private Customer(CustomerBuilder builder) {
        super(builder.id);
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public class CustomerBuilder {
        private int id;
        private String name;
        private String lastName;
        private String address;
        private String phoneNumber;

        public CustomerBuilder withId(int pId) {
            this.id = pId;
            return this;
        }

        public CustomerBuilder withName(String pName) {
            this.name = pName;
            return this;
        }

        public CustomerBuilder withLastName(String pLastName) {
            this.lastName = pLastName;
            return this;
        }

        public CustomerBuilder withAddress(String pAddress) {
            this.address = pAddress;
            return this;
        }

        public CustomerBuilder withPhoneNumber(String pPhoneNumber) {
            this.phoneNumber = pPhoneNumber;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
