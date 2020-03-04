package nl.tudelft.oopp.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_fk"})})
public class Restaurant {
    @Id
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
}
