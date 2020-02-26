package nl.tudelft.oopp.demo.onetoonebi;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int lib_id;

    private String name;
    private int capacity;

    /*Now setting up the foreign key. The foreign key in the Library table will point to the PK of Address
    The @JoinColumn defines foreign key column and indicates the owner of the relationship.
    unique = true enforces the unique constraint, 1 address belongs to only 1 library.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_fk", referencedColumnName = "add_id")
    private Address address;

    public Library() {}

    public Library(String name, int capacity, Address address) {
        this.name = name;
        this.capacity = capacity;
        this.address.setLibrary(this);
    }

    public int getId() {
        return lib_id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
