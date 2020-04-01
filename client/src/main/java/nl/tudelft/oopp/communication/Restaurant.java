package nl.tudelft.oopp.communication;

public class Restaurant {
    private int resId;
    private String restaurantName;
    private Menu menu;
    private Building building;

    public Restaurant() {
    }

    public Restaurant(int resId, String restaurantName) {
        this.resId = resId;
        this.restaurantName = restaurantName;
    }

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

    public String toString() {
        String res = this.restaurantName;
        return res;
    }
}
