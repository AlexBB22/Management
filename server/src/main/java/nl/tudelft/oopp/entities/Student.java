package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Email")
    private String email;

    @Column(name = "Age")
    private int age;


    public Student() {
    }

    public Student(int id, String Name, String Surname, String Email, int Age) {
        this.id = id;
        this.name = Name;
        this.surname = Surname;
        this.email = Email;
        this.age = Age;
    }

    public int getId() { return id; }
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}
