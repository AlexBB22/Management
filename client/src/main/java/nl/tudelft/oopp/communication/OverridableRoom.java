package nl.tudelft.oopp.communication;

public class OverridableRoom {
    private int roleID;
    private String roleName;
    private String userName;
    private String buildingName;
    private String roomName;
    private String name;
    private int roomID;
    private int capacity;
    private boolean clicker;
    private boolean powerOutlets;
    private boolean tv;
    private boolean whiteboard;
    private int reservationID;
    private int timeslotID;

    public OverridableRoom() {

    }

    public OverridableRoom(int roleID, String roleName, String userName, String buildingName, String roomName,
                           String name, int roomID, int capacity, boolean clicker, boolean powerOutlets,
                           boolean tv, boolean whiteboard, int reservationID, int timeslotID) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.userName = userName;
        this.buildingName = buildingName;
        this.roomName = roomName;
        this.name = name;
        this.roomID = roomID;
        this.capacity = capacity;
        this.clicker = clicker;
        this.powerOutlets = powerOutlets;
        this.tv = tv;
        this.whiteboard = whiteboard;
        this.reservationID = reservationID;
        this.timeslotID = timeslotID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getTimeslotID() {
        return timeslotID;
    }

    public void setTimeslotID(int timeslotID) {
        this.timeslotID = timeslotID;
    }

    @Override
    public String toString() {
        return "OverridableRoom{" +
                "roleID=" + roleID +
                ", roleName='" + roleName + '\'' +
                ", userName='" + userName + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", roomName='" + roomName + '\'' +
                ", name='" + name + '\'' +
                ", roomID=" + roomID +
                ", capacity=" + capacity +
                ", clicker=" + clicker +
                ", powerOutlets=" + powerOutlets +
                ", tv=" + tv +
                ", whiteboard=" + whiteboard +
                ", reservationID=" + reservationID +
                ", timeslotID=" + timeslotID +
                '}';
    }
}
