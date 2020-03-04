package nl.tudelft.oopp.communication;

public class Room {
    private int roomId;
    private int capacity;
    private String roomName;
    private Type type;
    private Building building;

    public int getRoom_id() {
        return roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getRoom_name() {
        return roomName;
    }

    public Type getType() {
        return type;
    }

    public Building getBuilding() {
        return building;
    }

    public void setRoom_id(int roomId) {
        this.roomId = roomId;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRoom_name(String roomName) {
        this.roomName = roomName;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
