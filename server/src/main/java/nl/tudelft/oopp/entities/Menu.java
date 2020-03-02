package nl.tudelft.oopp.entities;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu")

public class Menu {
    @Id
    private int menu_id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "menu_food", joinColumns = @JoinColumn(name = "menu_fk", referencedColumnName = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "food_fk", referencedColumnName = "food_id"))
    List<Food> foods = new ArrayList<Food>();

    @OneToOne(mappedBy = "menu")
    private Restaurant restaurant;

    public Menu() {}
    public Menu(int id) {
        this.menu_id = id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public List<Food> getFoods() {
        return foods;
    }
}
