package by.yakunina.copy.model;

import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.model.support.Identifiable;
import org.apache.commons.lang3.builder.Builder;

public class Employee implements Identifiable {

    private final EntityId id;
    private final String name;
    private final String lastName;
    private final String title;

    private Employee(EmployeeBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.title = builder.title;
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

    public String getTitle() {
        return title;
    }

    public static class EmployeeBuilder implements Builder<Employee> {

        private EntityId id;
        private String name;
        private String lastName;
        private String title;

        public EmployeeBuilder withId(EntityId pId) {
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
