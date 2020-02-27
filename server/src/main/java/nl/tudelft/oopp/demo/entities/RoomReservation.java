package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;

@Entity
@Table (name = "roomreservation")
public class RoomReservation {
    //getting the composite PK that uses two FK's to form the PK
    @EmbeddedId
    private RoomReservationCompositeID reservation_id;

    @Column
    private String day;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_fk", referencedColumnName = "role_id", insertable = false, updatable = false)
    private Role role;

    public RoomReservation() {}
    public RoomReservation(String day) {
        this.day = day;
    }

    public RoomReservationCompositeID getReservation_id() {
        return reservation_id;
    }

    public String getDay() {
        return day;
    }

    public Role getRole() {
        return role;
    }
}
