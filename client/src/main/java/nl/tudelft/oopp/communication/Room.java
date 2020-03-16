package nl.tudelft.oopp.communication;

public class Room {
    private int roomId;
    private int capacity;
    private String roomName;
    private Type type;
    private Building building;

    public Room() {
    }

    public Room(int capacity, String roomName) {
        this.capacity = capacity;
        this.roomName = roomName;
    }

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

    /**.
     * set the capacity to given integer, set to 0 if given integer below 0
     * @param capacity the capacity to set to
     * @Author Scott Jochems
     */
    public void setCapacity(int capacity) {
        if (capacity < 0) {
            this.capacity = 0;
        } else {
            this.capacity = capacity;
        }
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
