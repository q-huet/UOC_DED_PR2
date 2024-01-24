package uoc.ds.pr.model;

import edu.uoc.ds.adt.helpers.*;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.CTTCompaniesJobs;
import uoc.ds.pr.CTTCompaniesJobsPR2;

import java.util.*;

public class Role {
    private String id;

    private String name;

    private String description;

    List<Employee> RoleEmployees;

    public Role(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.RoleEmployees = new LinkedList<>();
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public Iterator<Employee> getEmployees() {
        return this.RoleEmployees.values();
    }

    public void addEmployee(Employee employee) {
        this.RoleEmployees.insertEnd(employee);
    }

    public void removeEmployee(Employee e1) {
        for (Iterator<Employee> it = getEmployees(); it.hasNext(); ) {
            final Position<Employee> employeePosition = (Position<Employee>) it.next();
            //final Posicion<Worker> workerPosition = it.siguiente();
            Employee e2 = employeePosition.getElem();
            if (e2.getEmployeeId().equals(e1.getEmployeeId())) {
                RoleEmployees.delete(employeePosition);
            }
        }
    }

}
