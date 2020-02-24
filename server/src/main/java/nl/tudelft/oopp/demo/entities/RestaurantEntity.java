package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")

public class RestaurantEntity {

    @Column(name="res_id")
    private int res_id;

    @Column(name="building")
    private String building;

    @Column(name="menu")
    private String menu;

    public RestaurantEntity(int res_id, String building, String menu){
        this.res_id=res_id;
        this.building=building;
        this.menu=menu;
    }

    public int getRes_id(){ return this.res_id; }

    public void setRes_id(int res_id){ this.res_id=res_id; }

    public String getBuilding(){ return this.building; }

    public void setBuilding(String building){ this.building=building; }

    public String getMenu(){ return this.menu; }

    public void setMenu(String menu){ this.menu=menu; }
}
