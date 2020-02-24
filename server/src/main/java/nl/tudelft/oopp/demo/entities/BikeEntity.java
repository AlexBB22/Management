package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "bike")
public class BikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idbike;

    @Column(name = "color")
    private String color;

    @Column(name = "available")
    private boolean available;

    public BikeEntity() {}

    public BikeEntity(int idbike, String color, boolean available) {
        this.idbike = idbike;
        this.color = color;
        this.available = available;
    }

    public int getIdbike() {
        return idbike;
    }

    public String getColor() {
        return color;
    }

    public boolean getAvailable() {
        return available;
    }

    public String toString() {
        String res = "Id: " + getIdbike() + ", color: " + getColor() + ", availability: " + getAvailable();
        return res;
    }
}
