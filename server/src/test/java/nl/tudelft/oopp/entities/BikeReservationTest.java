package nl.tudelft.oopp.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BikeReservationTest {

    @Test
    public void bikeReservationConstructorTest(){
        assertNotNull(new BikeReservation());
    }

    @Test
    public void getBikeReservationId(){
        BikeReservation br1 = new BikeReservation();
        assertEquals(0, br1.getId());
    }

    @Test
    public void getBikeReservationUserId(){
        BikeReservation br1 = new BikeReservation();
        assertNull(br1.getBike_user_fk());
    }

    @Test
    public void setBikeReservationUserId(){
        BikeReservation br1 = new BikeReservation();
        User anton = new User(0, "a", "b", "c");
        br1.setBike_user_fk(anton);
        assertEquals(anton, br1.getBike_user_fk());
    }

    @Test
    public void getBikeFk(){
        BikeReservation b1 = new BikeReservation();
        assertNull(b1.getBike_fk());
    }

    @Test
    public void setBikeFk(){
        BikeReservation br1 = new BikeReservation();
        Bike b = new Bike();
        br1.setBike_fk(b);
        assertEquals(b, br1.getBike_fk());
    }

    @Test
    public void setBikeFkNotNull(){
        BikeReservation br1 = new BikeReservation();
        Bike b = new Bike();
        br1.setBike_fk(b);
        assertNotNull(br1.getBike_fk());
    }

    @Test
    public void setUserIdNotNull(){
        BikeReservation br1 = new BikeReservation();
        User anton = new User(0, "a", "b", "c");
        br1.setBike_user_fk(anton);
        assertNotNull(br1.getBike_user_fk());
    }

}
