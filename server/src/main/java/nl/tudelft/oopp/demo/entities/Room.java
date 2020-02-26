package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private int id;

    @Column(name = "capacity")
    private int capacity;

    @ManyToMany
    @JoinTable(name = "ROOM_RESERVATIONS", joinColumns = { @JoinColumn(name="id") }, inverseJoinColumns = { @JoinColumn(name="reservation_id") })
    private List<Reservation> reservations = new ArrayList<Reservation>();

    public Room(int id, int capacity){
        this.capacity=capacity;
        this.id=id;
    }
    public Room(){
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
