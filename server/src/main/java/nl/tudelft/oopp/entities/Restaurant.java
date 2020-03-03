package nl.tudelft.oopp.entities;
import nl.tudelft.oopp.entities.Building;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int res_id;

    @OneToOne
    @JoinColumn(name = "menu_fk", referencedColumnName = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_fk")
    private Building building;

    public Restaurant() {}
    public Restaurant(int res_id) {
        this.res_id = res_id;
    }

    public int getRes_id() {
        return res_id;
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
