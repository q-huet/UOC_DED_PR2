package uoc.ds.pr.model;

import edu.uoc.ds.adt.helpers.*;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.traversal.*;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.CTTCompaniesJobs;
import uoc.ds.pr.CTTCompaniesJobsPR2;

import java.util.*;

public class Role {
    private String id;

    private String description;

    List<Employee> RoleEmployees;

    int numEmployees;

    public Role(String id, String description) {
        this.id = id;
        this.description = description;
        this.RoleEmployees = new LinkedList<>();
    }

    public void update(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Iterator<Employee> getEmployees() {
        return this.RoleEmployees.values();
    }

    public int getNumEmployees(){
        return RoleEmployees.size();
    }

    public void addEmployee(Employee employee) {
        this.RoleEmployees.insertEnd(employee);
    }

    public void removeEmployee(Employee employee) {
        Traversal<Employee> traversal = RoleEmployees.positions();
        while (traversal.hasNext()) {
            Position<Employee> currentPos = traversal.next();
            Employee currentEmployee = currentPos.getElem();

            if (currentEmployee.getEmployeeId().equals(employee.getEmployeeId())) {
                RoleEmployees.delete(currentPos);
                return;
            }
        }
    }

}
