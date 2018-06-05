package by.yakunina.copy.service;

import by.yakunina.copy.model.Employee;
import by.yakunina.copy.model.support.EntityId;

public class EmployeeService {

    public int createEmployee(Employee employee) {
        return -1;
    }

    public Employee findEmployee(EntityId id) {
        return new Employee.EmployeeBuilder()
                .build();
    }

    public void updateEmployee(Employee employee) {

    }

    public void deleteEmployee(EntityId id) {
        
    }
}
