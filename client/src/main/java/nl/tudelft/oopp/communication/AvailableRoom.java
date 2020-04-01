package nl.tudelft.oopp.communication;

public class AvailableRoom {
    private String buildingName;

    private String roomName;

    private String name;

    private int roomID;

    private int capacity;

    private boolean clicker;

    private boolean powerOutlets;

    private boolean tv;

    private boolean whiteboard;

    public AvailableRoom() {

    }

    /**
     * Constructor for AvailableRoom object.
     * @author Kanish Dwivedi
     * @param buildingName - the name of the building.
     * @param roomName the name of the room
     * @param name - the type of the room
     * @param roomID - the id of the room
     * @param capacity - the capacity of the room
     * @param clicker - if the room has a clicker or not
     * @param powerOutlets - if the room has a power outlet or not
     * @param tv - if the room has a tv or not
     * @param whiteboard - if the room has a whiteboard or not
     */
    public AvailableRoom(String buildingName, String roomName, String name, int roomID, int capacity, boolean clicker,
                         boolean powerOutlets, boolean tv, boolean whiteboard) {
        this.buildingName = buildingName;
        this.roomName = roomName;
        this.name = name;
        this.roomID = roomID;
        this.capacity = capacity;
        this.clicker = clicker;
        this.powerOutlets = powerOutlets;
        this.tv = tv;
        this.whiteboard = whiteboard;
    }

    @Override
    public String toString() {
        return "AvailableRoom{"
                + "buildingName='" + buildingName + '\''
                + ", roomName='" + roomName + '\''
                + ", name='" + name + '\''
                + ", roomID=" + roomID
                + ", capacity=" + capacity
                + ", clicker=" + clicker
                + ", powerOutlets=" + powerOutlets
                + ", tv=" + tv
                + ", whiteboard=" + whiteboard
                + '}';
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isClicker() {
        return clicker;
    }

    public void setClicker(boolean clicker) {
        this.clicker = clicker;
    }

    public boolean isPowerOutlets() {
        return powerOutlets;
    }

    public void setPowerOutlets(boolean powerOutlets) {
        this.powerOutlets = powerOutlets;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isWhiteboard() {
        return whiteboard;
    }

    public void setWhiteboard(boolean whiteboard) {
        this.whiteboard = whiteboard;
    }
}
