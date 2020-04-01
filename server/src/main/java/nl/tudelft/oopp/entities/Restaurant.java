package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_fk"})})
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int resId;

    @Column(name = "restaurantName")
    private String restaurantName;

    @OneToOne
    @JoinColumn(name = "menu_fk", referencedColumnName = "menu_id", unique = true)
    private Menu menu;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "building_fk")
    private Building building;

    public Restaurant() {
    }

    public Restaurant(int resId, String restaurantName) {
        this.resId = resId;
        this.restaurantName = restaurantName;
    }

    @JsonManagedReference(value = "restaurantMenu")
    public Menu getMenu() {
        return menu;
    }


    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {

        this.building = building;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
