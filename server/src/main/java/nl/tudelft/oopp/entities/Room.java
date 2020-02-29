package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private int id;

    @Column(name = "capacity")
    private int capacity;

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
