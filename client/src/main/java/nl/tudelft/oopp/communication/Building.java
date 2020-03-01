package nl.tudelft.oopp.communication;

public class Building {
    private boolean non_reservable_space;
    private int car_parking_spaces;
    private String description;
    private String opening;
    private String closing;
    private String building_Name;

    public boolean isNon_reservable_space() {
        return non_reservable_space;
    }

    public int getCar_parking_spaces() {
        return car_parking_spaces;
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
        return building_Name;
    }

    public void setNon_reservable_space(boolean non_reservable_space) {
        this.non_reservable_space = non_reservable_space;
    }

    public void setCar_parking_spaces(int car_parking_spaces) {
        this.car_parking_spaces = car_parking_spaces;
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

    public void setBuilding_Name(String building_Name) {
        this.building_Name = building_Name;
    }
}
