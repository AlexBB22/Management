package nl.tudelft.oopp.communication;

public class Room {
    private int room_id;
    private int capacity;
    private String room_name;
    private Type type;
    private Building building;

    public int getRoom_id() {
        return room_id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getRoom_name() {
        return room_name;
    }

    public Type getType() {
        return type;
    }

    public Building getBuilding() {
        return building;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
