package nl.tudelft.oopp.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Food {
    @Id
    //food_id is representing the name of the food
    @Column(name = "food_id")
    private String foodId;
    private int price;

    public Food() {
    }

    public Food(String foodId, int price) {
        this.foodId = foodId;
        this.price = price;
    }

    public String getFood_id() {
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
}
