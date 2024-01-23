package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.*;
import uoc.ds.pr.*;

public class Equipment {
    private String id;

    private String name;

    private String description;

    private Room room;

    public Equipment(String id, String name, String description) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
       // this.setRoom(room);
    }

    public void update(String name, String description, Room room) {
        this.setName(name);
        this.setDescription(description);
    //    this.setRoom(room);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
