package nl.tudelft.oopp.demo.entities;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type")
public class Type {
    @Id
    private int type_id;

    @NotNull
    @Column(name = "whiteboard")
    private boolean whiteBoard;

    @NotNull
    @Column(name = "tv")
    private boolean tv;

    @NotNull
    @Column(name = "power_outlets")
    private boolean powerOutlets;

    @NotNull
    @Column(name = "clicker")
    private boolean clicker;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Room> listOfRooms;

    public Type(){
        this.type_id = type_id;
        this.whiteBoard = whiteBoard;
        this.powerOutlets = powerOutlets;
        this.clicker = clicker;
        listOfRooms = new ArrayList<Room>();
    }

    public int getType_id() {
        return type_id;
    }

    public boolean isWhiteBoard() {
        return whiteBoard;
    }

    public boolean isTv() {
        return tv;
    }

    public boolean isPowerOutlets() {
        return powerOutlets;
    }

    public boolean isClicker() {
        return clicker;
    }

    public void setWhiteBoard(boolean whiteBoard) {
        this.whiteBoard = whiteBoard;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public void setPowerOutlets(boolean powerOutlets) {
        this.powerOutlets = powerOutlets;
    }

    public void setClicker(boolean clicker) {
        this.clicker = clicker;
    }

    public List<Room> getListOfRooms() {
        return listOfRooms;
    }

    public void setListOfRooms(List<Room> listOfRooms) {
        this.listOfRooms = listOfRooms;
    }
}
