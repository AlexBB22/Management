package nl.tudelft.oopp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void constructorUser() {
        User user = new User(2, "email", "username", "password");
        assertNotNull(user);
    }

    @Test
    void emptyConstructorTest() {
        User u2 = new User();
        assertNotNull(u2);
    }

    @Test
    public void getSetRole() {
        User user = new User(2, "email", "username", "password");
        Role role = new Role(1, "admin");
        user.setRole(role);
        assertEquals(role, user.getRole());
    }

    @Test
    public void getSetUserName() {
        User user = new User(2, "email", "username", "password");
        user.setUser_name("notUsername");
        assertEquals("notUsername", user.getUser_name());
    }

    @Test
    public void getSetPassword() {
        User user = new User(2, "email", "username", "password");
        user.setUser_password("notPassword");
        assertEquals("notPassword", user.getUser_password());
    }

    @Test
    public void getSetUserID() {
        User user = new User(2, "email", "username", "password");
        user.setUser_id(5);
        assertEquals(5, user.getUser_id());
    }

    @Test
    public void getSetEmail() {
        User user = new User(2, "email", "username", "password");
        user.setEmail("notEmail");
        assertEquals("notEmail", user.getEmail());
    }

    @Test
    public void getSetRoomReservations() {
        User user = new User(2, "email", "username", "password");
        List<RoomReservation> roomReservList = new ArrayList<RoomReservation>();
        user.setRoomReservations(roomReservList);
        assertEquals(roomReservList, user.getRoomReservations());
    }

    @Test
    public void addRemoveRoomReservations() {
        User user = new User(2, "email", "username", "password");
        List<RoomReservation> roomReservList = new ArrayList<RoomReservation>();
        Date date = new Date(2020, 3, 10);
        RoomReservation rs = new RoomReservation(date);
        user.setRoomReservations(roomReservList);
        user.addRoomReservation(rs);
        assertEquals(rs, user.getRoomReservations().get(0));
        user.removeRoomReservation(rs);
        assertEquals(0, user.getRoomReservations().size());
    }

    @Test
    public void toStringTest() {
        User user = new User(2, "email", "username", "password");
        Role role = new Role(1, "admin");
        user.setRole(role);
        String toString = "user_id: " + user.getUser_id() + " , email: " + user.getEmail()
                + " , user_name: " + user.getUser_name() + " , user_password: " + user.getUser_password() + " , role_fk: " + user.getRole().getRole_id();
        System.out.println(toString);
        System.out.println(user.toString());
        assertEquals(toString, user.toString());
    }

    @Test
    void getBikeReservationsTest() {
        User u1 = new User(1, "123@test", "Kanish", "password");
        Bike b1 = new Bike();
        Bike b2 = new Bike();
        BikeReservation br1 = new BikeReservation(new Date(20200401));
        br1.setBike_fk(b1);
        BikeReservation br2 = new BikeReservation(new Date(20200402));
        br1.setBike_fk(b2);
        List<BikeReservation> bikeReservations = new ArrayList<>();
        bikeReservations.add(br1);
        bikeReservations.add(br2);

        u1.setBikeReservations(bikeReservations);
        assertEquals(bikeReservations, u1.getBikeReservations());
    }

    @Test
    void setBikeReservationsTest() {
        User u1 = new User(1, "123@test", "Kanish", "password");
        Bike b1 = new Bike();
        Bike b2 = new Bike();
        BikeReservation br1 = new BikeReservation(new Date(20200401));
        br1.setBike_fk(b1);
        BikeReservation br2 = new BikeReservation(new Date(20200402));
        br1.setBike_fk(b2);
        List<BikeReservation> bikeReservations = new ArrayList<>();
        bikeReservations.add(br1);
        bikeReservations.add(br2);

        u1.setBikeReservations(bikeReservations);

        Bike b3 = new Bike();
        BikeReservation br3 = new BikeReservation(new Date(20200404));
        br3.setBike_fk(b3);
        bikeReservations.add(br3);

        List<BikeReservation> newList = new ArrayList<>();
        newList.addAll(bikeReservations);
        u1.setBikeReservations(newList);

        assertEquals(newList, u1.getBikeReservations());
    }

    @Test
    void addBikeReservationsTest() {
        User u1 = new User(1, "123@test", "Kanish", "password");
        Bike b1 = new Bike();
        Bike b2 = new Bike();
        BikeReservation br1 = new BikeReservation(new Date(20200401));
        br1.setBike_fk(b1);
        BikeReservation br2 = new BikeReservation(new Date(20200402));
        br1.setBike_fk(b2);
        List<BikeReservation> bikeReservations = new ArrayList<>();
        bikeReservations.add(br1);

        u1.setBikeReservations(bikeReservations);
        u1.addBikeReservation(br2);

        List<BikeReservation> actual = new ArrayList<>();
        actual.add(br1);
        actual.add(br2);

        assertEquals(actual, u1.getBikeReservations());
    }

    @Test
    void removeBikeReservationsTest() {
        User u1 = new User(1, "123@test", "Kanish", "password");
        Bike b1 = new Bike();
        Bike b2 = new Bike();
        BikeReservation br1 = new BikeReservation(new Date(20200401));
        br1.setBike_fk(b1);
        BikeReservation br2 = new BikeReservation(new Date(20200402));
        br1.setBike_fk(b2);
        List<BikeReservation> bikeReservations = new ArrayList<>();
        bikeReservations.add(br1);
        bikeReservations.add(br2);

        u1.setBikeReservations(bikeReservations);
        u1.removeBikeReservation(br2);

        List<BikeReservation> actual = new ArrayList<>();
        actual.add(br1);

        assertEquals(actual, u1.getBikeReservations());

    }

}
