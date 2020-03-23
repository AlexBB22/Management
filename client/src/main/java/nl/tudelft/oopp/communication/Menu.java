package nl.tudelft.oopp.communication;

import java.util.List;

public class Menu {
    private int menuId;
    private List<Food> foods;
    private Restaurant restaurant;

    public Menu(){}

    public Menu(int id) {
        this.menuId = id;
    }

    public int getMenuId() {
        return menuId;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
