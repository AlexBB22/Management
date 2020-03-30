package nl.tudelft.oopp.communication;

public class Restaurant {
    private int resId;
    private Menu menu;
    private Building building;

    public Restaurant() {
    }

    public Restaurant(int resId) {
        this.resId = resId;
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

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
