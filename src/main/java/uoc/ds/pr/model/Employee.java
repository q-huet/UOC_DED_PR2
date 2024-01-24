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

    private String employeeId;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    private String role;

    private List<Room> assignedRooms;

    public Employee(String dni, String name, String surname,
                    LocalDate dateOfBirth, String role) {
        this.employeeId = dni;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.assignedRooms = new LinkedList<>();
    }

    public void update(String name, String surname,
                       LocalDate dateOfBirth, String role) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public Iterator<Room> getRooms() {
        return this.assignedRooms.values();
    }

    public int numAssignedRooms() {
        return assignedRooms.size();
    }

    public void assignRoom(Room room) {
        if (!isAssignedToRoom(room)) {
            this.assignedRooms.insertEnd(room);
        }
    }

    public boolean isAssignedToRoom(Room room) {
        Iterator<Room> it = this.assignedRooms.values();
        while (it.hasNext()) {
            Room assignedRoom = it.next();
            if (assignedRoom.equals(room)) return true;
        }
        return false;
    }

    public void removeRoom(Room r1) {
        for (Iterator<Room> it = getRooms(); it.hasNext(); ) {
            final Position<Room> roomPosition = (Position<Room>) it.next();
            Room r2 = roomPosition.getElem();
            if (r2.getRoomId().equals(r1.getRoomId())) {
                assignedRooms.delete(roomPosition);
            }
        }
    }


}
