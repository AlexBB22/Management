package nl.tudelft.oopp.communication;

public class Building {
    private boolean nonReservableSpace;
    private int carParkingSpaces;
    private String description;
    private String opening;
    private String closing;
    private String buildingName;

    public boolean isNon_reservable_space() {
        return nonReservableSpace;
    }

    public int getCar_parking_spaces() {
        return carParkingSpaces;
    }

    public String getDescription() {
        return description;
    }

    public String getOpening() {
        return opening;
    }

    public String getClosing() {
        return closing;
    }

    public String getBuilding_Name() {
        return buildingName;
    }

    public void setNon_reservable_space(boolean nonReservableSpace) {
        this.nonReservableSpace = nonReservableSpace;
    }

    public void setCar_parking_spaces(int carParkingSpaces) {
        this.carParkingSpaces = carParkingSpaces;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public void setBuilding_Name(String buildingName) {
        this.buildingName = buildingName;
    }
}
