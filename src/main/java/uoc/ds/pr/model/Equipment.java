package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.*;
import uoc.ds.pr.*;

public class Equipment {
    private String id;

    private String name;

    private String description;

    private Room assignedToRoom;

    public Equipment(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignedToRoom = null;
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Room getRoom() {
        return assignedToRoom;
    }

    public void assignedToRoom(Room room) {
        if (this.assignedToRoom != null) this.assignedToRoom.removeEquipment(this);
        this.assignedToRoom = room;
        if (room != null) room.addEquipment(this);

    }
}
