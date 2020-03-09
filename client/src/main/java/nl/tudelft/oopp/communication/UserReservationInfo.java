package nl.tudelft.oopp.communication;

public class UserReservationInfo {
    private int reservationID;
    private String day;
    private String startTime;
    private String endTime;
    private String buildingName;
    private String roomName;
    private String name;

    public UserReservationInfo() {

    }

    /**
     * Constructor for a UserReservationInfo object.
     * Used by Jackson to map the server side UserReservationInfoProjection object to an object on client side.
     * @author Kanish Dwivedi
     * @param reservationID - the id of the reservation made by the user
     * @param day - the date of reservation
     * @param startTime - the start time of the reservation
     * @param endTime - the end time of the reservation
     * @param buildingName - the name of the building where the room is
     * @param roomName - the name of the room
     * @param name - the name of the type of the room
     */
    public UserReservationInfo(int reservationID, String day, String startTime, String endTime,
                               String buildingName, String roomName, String name) {
        this.reservationID = reservationID;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.buildingName = buildingName;
        this.roomName = roomName;
        this.name = name;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    @Override
    public String toString() {
        return "UserReservationInfo{"
                + "reservationID=" + reservationID + ", day='" + day + '\'' + ", startTime='" + startTime + '\''
                + ", endTime='" + endTime + '\'' + ", buildingName='" + buildingName + '\'' + ", roomName='" + roomName
                + '\'' + ", name='" + name + '\'' + '}';
    }
}
