package uoc.ds.pr.model;

import edu.uoc.ds.adt.helpers.*;
import edu.uoc.ds.adt.nonlinear.graphs.*;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.traversal.*;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.CTTCompaniesJobs;
import uoc.ds.pr.CTTCompaniesJobsPR2;
import uoc.ds.pr.CTTCompaniesJobsPR2.RoomType;

import java.util.*;

public class Room implements Comparable<Room>{
    private String id;

    private String name;

    private String description;

    private RoomType type;

    List<Employee> employees;

    List<Equipment> equipments;

    public static final Comparator<Room> CMP_R = Comparator.comparingInt(Room::numEquipments);

    public Room(String id, String name, String description, RoomType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.employees = new LinkedList<>();
        this.equipments = new LinkedList<>();
    }

    public void update(String name, String description, RoomType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getRoomId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public RoomType getType() {
        return type;
    }

    public int numEquipments() {
        return this.equipments.size();
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

    public Iterator<Equipment> getEquipments() {
        return this.equipments.values();
    }

    public void addEquipment(Equipment equipment) {
        for (Iterator<Equipment> it = getEquipments(); it.hasNext(); ) {
            if (it.next().getId().equals(equipment.getId())) {
                return;
            }
        }
        this.equipments.insertEnd(equipment);
    }

    public void removeEquipment(Equipment equipment) {
        Traversal<Equipment> traversal = equipments.positions();

        while (traversal.hasNext()) {
            Position<Equipment> currentPos = traversal.next();
            Equipment currentEquip = currentPos.getElem();

            if (currentEquip.getId().equals(equipment.getId())) {
                equipments.delete(currentPos);
                return;
            }
        }
    }

    @Override
    public int compareTo(Room o) {
        return 0;
    }
}
