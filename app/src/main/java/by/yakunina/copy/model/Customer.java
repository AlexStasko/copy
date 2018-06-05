package by.yakunina.copy.model;

import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.model.support.Identifiable;

/**
 * Customer entity.
 */
public class Customer implements Identifiable {

    private final EntityId id;
    private final String name;
    private final String lastName;
    private final String address;
    private final String phoneNumber;

    private Customer(CustomerBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
    }

    @Override
    public EntityId getId() {
        return id;
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

    public static class CustomerBuilder {
        private EntityId id;
        private String name;
        private String lastName;
        private String address;
        private String phoneNumber;

        public CustomerBuilder withId(EntityId pId) {
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
