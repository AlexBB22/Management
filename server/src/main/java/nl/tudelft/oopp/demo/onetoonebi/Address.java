package nl.tudelft.oopp.demo.onetoonebi;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int add_id;

    private String street;
    private String zipCode;

    /*
    The mappedBy is used to indicate that this entity is having its primary key being used as by a fk
    We then name the column name of the other table that is the fk that is using this entities PK.
     */
    @OneToOne(mappedBy = "address")
    private Library library;

    public Address() {}

    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }

    public int getAdd_id() {
        return add_id;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    @JsonIgnore
    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

}
