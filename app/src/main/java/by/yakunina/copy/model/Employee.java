package by.yakunina.copy.model;

import by.yakunina.copy.model.support.Entity;
import org.apache.commons.lang3.builder.Builder;

public class Employee extends Entity {

    private final String name;
    private final String lastName;
    private final String title;

    private Employee() {
        super(-1);
        this.name = null;
        this.lastName = null;
        this.title = null;
    }

    private Employee(EmployeeBuilder builder) {
        super(builder.id);
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.title = builder.title;
    }

    public static class EmployeeBuilder implements Builder<Employee> {

        private int id;
        private String name;
        private String lastName;
        private String title;

        public EmployeeBuilder withId(int pId) {
            this.id = pId;
            return this;
        }

        public EmployeeBuilder withName(String pName) {
            this.name = pName;
            return this;
        }

        public EmployeeBuilder withLastName(String pLastName) {
            this.lastName = pLastName;
            return this;
        }

        public EmployeeBuilder withTitle(String pTitle) {
            this.title = pTitle;
            return this;
        }

        @Override
        public Employee build() {
            return new Employee(this);
        }
    }
}
