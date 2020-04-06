package nl.tudelft.oopp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "food")
public class Food {
    @Id
    @Column(name = "food_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int foodId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @ManyToMany(mappedBy = "foods")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Menu> menus = new ArrayList<Menu>();

    public Food() {
    }

    public Food(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getPrice() {
        return price;
    }

    /**.
     * set price of food if given price below 0 then price will be 0
     * @param price an integer indicating the price to be set
     * @Author Scott Jochems
     */
    public void setPrice(int price) {
        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonBackReference(value = "foodMenu")
    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
