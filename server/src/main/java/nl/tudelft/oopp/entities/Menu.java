package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "menu_id")
    private int menuId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "menu_food", joinColumns = @JoinColumn(name = "menu_fk", referencedColumnName = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "food_fk", referencedColumnName = "food_Id"))
    List<Food> foods = new ArrayList<Food>();

    @OneToOne(mappedBy = "menu")
    private Restaurant restaurant;

    public Menu() {
    }

    public Menu(int id) {
        this.menuId = id;
    }

    public int getMenuId() {
        return menuId;
    }

    @JsonManagedReference(value = "foodMenu")
    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    @JsonBackReference(value = "restaurantMenu")
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
