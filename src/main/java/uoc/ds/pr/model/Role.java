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
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.RoleEmployees = new LinkedList<>();
    }

    public void update(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
