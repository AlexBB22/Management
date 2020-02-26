import java.util.ArrayList;
import java.util.List;

public class Building {
    public List<Room> roomList;
    public String name;
    public int roomAmount;
    List<String> desc;

    public Building(String name, int roomAmount){
        this.name = name;
        this.roomAmount = roomAmount;
        this.roomList = new ArrayList<Room>();
        this.desc = new ArrayList<String>();
    }

    // this needs to be added, maybe using a scanner
    // public void addRoom()

    // returns the amount of rooms still available
    // public  getAvailableRooms()

    // returns the type of rooms still available
    // public  getTypeOfAvailableRooms

    // returns the number of rooms per available room type (for example, 3 lecture halls still available)
    // public checkAvailability

    // remove room
    // public removeRemove()

    public boolean equals(Object other){
        if (other instanceof Building){
            Building that = (Building) other;
            if (this.name.equals(that.name)){
                return true;
            }
        }
        return false;
    }


}
