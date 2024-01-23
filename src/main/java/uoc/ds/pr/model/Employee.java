package uoc.ds.pr.model;

import edu.uoc.ds.adt.helpers.*;
import edu.uoc.ds.adt.nonlinear.graphs.*;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.CTTCompaniesJobs;
import uoc.ds.pr.CTTCompaniesJobsPR2;

import java.time.*;

public class Employee {

    private String DNI;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    private Role role;

    private List<Room> rooms;

    private Graph<Employee, Employee> socialNetwork;

    public Employee(String dni, String name, String surname,
                    LocalDate dateOfBirth, Role role) {
        this.setDNI(dni);
        this.setName(name);
        this.setSurname(surname);
        this.setDateOfBirth(dateOfBirth);
        this.setRole(role);
        this.rooms = new LinkedList<>();
        this.socialNetwork = new DirectedGraphImpl<>();
    }


    public void update(String name, String surname,
                       LocalDate dateOfBirth, Role role) {
        this.setName(name);
        this.setSurname(surname);
        this.setDateOfBirth(dateOfBirth);
        this.setRole(role);
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Iterator<Room> getRooms() {
        return this.rooms.values();
    }

    public void addRoom(Room room) {
        this.rooms.insertEnd(room);
    }

    public void removeRoom(Room r1) {
        for (Iterator<Room> it = getRooms(); it.hasNext(); ) {
            final Position<Room> roomPosition = (Position<Room>) it.next();
            Room r2 = roomPosition.getElem();
            if (r2.getId().equals(r1.getId())) {
                rooms.delete(roomPosition);
            }
        }
    }


}
