package nl.tudelft.oopp.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "menu")

public class Menu {
    @Id
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

    public int getMenu_id() {
        return menuId;
    }

    public List<Food> getFoods() {
        return foods;
    }
}
