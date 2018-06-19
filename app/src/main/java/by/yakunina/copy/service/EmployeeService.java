package by.yakunina.copy.service;

import by.yakunina.copy.model.Employee;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.support.KeyGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeService {

    private static List<Employee> employees = new ArrayList<>();

    static {
        Employee employee = new Employee.EmployeeBuilder()
                .withId(new EntityId(KeyGenerator.getUUID()))
                .withName("Vasili")
                .withLastName("Pupkin")
                .withTitle("Worker")
                .build();
        employees.add(employee);
        employee = new Employee.EmployeeBuilder()
                .withId(new EntityId(KeyGenerator.getUUID()))
                .withName("Irina")
                .withLastName("Nechaeva")
                .withTitle("Manager")
                .build();
        employees.add(employee);
    }

    public EntityId createEmployee(Employee employee) {
        employee.setId(new EntityId(KeyGenerator.getUUID()));
        employees.add(employee);
        return employee.getId();
    }

    public Employee findEmployee(String id) {
        for (Employee employee : employees) {
            if (employee.getId().getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public void updateEmployee(Employee employee) {

    }

    public void deleteEmployee(EntityId id) {
        
    }

    public List<Employee> findAll() {
        return employees;
    }
}
