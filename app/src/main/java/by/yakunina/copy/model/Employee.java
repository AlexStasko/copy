package by.yakunina.copy.model;

import by.yakunina.copy.model.support.EntityId;
import org.apache.commons.lang3.builder.Builder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Employee extends User {

    private String title;

    private Employee(EmployeeBuilder builder) {
        super(builder.id, builder.name, builder.lastName);
        this.title = builder.title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("title", title)
                .toString();
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
