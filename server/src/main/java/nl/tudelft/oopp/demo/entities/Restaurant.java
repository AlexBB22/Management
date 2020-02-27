package nl.tudelft.oopp.demo.entities;
import javax.persistence.*;
import javax.validation.constraints.*;
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    private int res_id;

    @NotNull
    @Column(name = "Building")
    private String buildingName;

    @NotNull
}
