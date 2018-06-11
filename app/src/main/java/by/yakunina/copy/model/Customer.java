package by.yakunina.copy.model;

import by.yakunina.copy.model.support.EntityId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Customer entity.
 */
public class Customer extends User {

    private String address;
    private String phoneNumber;

    private Customer(CustomerBuilder builder) {
        super(builder.id, builder.name, builder.lastName);
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
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
                .appendSuper(super.toString())
                .append("address", address)
                .append("phoneNumber", phoneNumber)
                .toString();
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
