package uoc.ds.pr.model;

import edu.uoc.ds.adt.helpers.*;
import edu.uoc.ds.adt.nonlinear.graphs.*;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.CTTCompaniesJobs;
import uoc.ds.pr.CTTCompaniesJobsPR2;

import java.util.*;

public class Room {
    private String id;

    private String name;

    private String description;

    private CTTCompaniesJobsPR2.RoomType type;

    List<Employee> employees;

    List<Equipment> equipment;

    public Room(String id, String name, String description, CTTCompaniesJobsPR2.RoomType type) {
        this.setRoomId(id);
        this.setName(name);
        this.setDescription(description);
        this.setType(type);
        this.employees = new LinkedList<>();
        this.equipment = new LinkedList<>();
    }

    public void update(String name, String description, CTTCompaniesJobsPR2.RoomType type) {
        this.setName(name);
        this.setDescription(description);
        this.setType(type);
    }

    public String getRoomId() {
        return id;
    }

    public void setRoomId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public CTTCompaniesJobsPR2.RoomType getType() {
        return type;
    }

    public void setType(CTTCompaniesJobsPR2.RoomType type) {
        this.type = type;
    }

    public int numEquipments(){
        return this.equipment.size();
    }

    public Iterator<Employee> getEmployees() {
        return this.employees.values();
    }

    public void addEmployee(Employee employee) {
        this.employees.insertEnd(employee);
    }

    public void removeEmployee(Employee e1) {
        for (Iterator<Employee> it = getEmployees(); it.hasNext(); ) {
            final Position<Employee> employeePosition = (Position<Employee>) it.next();
            Employee e2 = employeePosition.getElem();
            if (e2.getEmployeeId().equals(e1.getEmployeeId())) {
                employees.delete(employeePosition);
            }
        }
    }
    public Iterator<Equipment> getEquipment () {
        return this.equipment.values();
    }

    public void addEquipment(Equipment equipment) {
        this.equipment.insertEnd(equipment);
    }

    public void removeEquipment(Equipment eq1) {
        for (Iterator<Equipment> it = getEquipment(); it.hasNext(); ) {
            final Position<Equipment> equipmentPosition = (Position<Equipment>) it.next();
            Equipment eq2 = equipmentPosition.getElem();
            if (eq2.getId().equals(eq1.getId())) {
                equipment.delete(equipmentPosition);
            }
        }
    }
}
