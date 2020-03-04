package nl.tudelft.oopp.entities;

import javax.persistence.*;


@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_fk"})})
public class Restaurant {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int resId;

    @OneToOne
    @JoinColumn(name = "menu_fk", referencedColumnName = "menu_id", unique = true)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_fk")
    private Building building;

    public Restaurant() {
    }

    public Restaurant(int resId) {
        this.resId = resId;
    }

    public int getRes_id() {
        return resId;
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

    public void setMenu(Menu menu) {
        this.menu=menu;
    }

}
