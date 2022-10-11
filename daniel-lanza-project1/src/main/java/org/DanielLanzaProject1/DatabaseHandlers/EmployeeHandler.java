package org.DanielLanzaProject1.DatabaseHandlers;

import org.DanielLanzaProject1.DataTypes.Employee;
import org.DanielLanzaProject1.DatabaseSQL.EmployeeDatabase;

import java.util.List;

public class EmployeeHandler {

    private EmployeeDatabase employeeDb;

    public EmployeeHandler(){
        employeeDb = new EmployeeDatabase();
    }

    public EmployeeHandler(EmployeeDatabase employeeDb){
        this.employeeDb = employeeDb;
    }


    public int createEmployee(Employee employee){
        return employeeDb.create(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeDb.getAll();
    }

    public Employee getByID(int id){
        return employeeDb.getId(id);
    }

    public Employee updateEmployee(Employee employee){
        return employeeDb.update(employee);
    }

    public String deleteEmployee(Employee employee){
        if(employeeDb.delete(employee)){
            return "Manager has been successfully deleted";
        }else{
            return "Manager could not be deleted";
        }
    }

}
