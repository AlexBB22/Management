package nl.tudelft.oopp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Food {
    @Id
    //food_id is representing the name of the food
    private String food_id;
    private int price;

    public Food() {}
    public Food(String food_id, int price) {
        this.food_id = food_id;
        this.price = price;
    }

    public String getFood_id() {
        return food_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
