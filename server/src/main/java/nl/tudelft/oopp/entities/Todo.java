package nl.tudelft.oopp.entities;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This entity class is there so that a user can add a new Todo item on their weekly agende on client side main menu.
 * @author Kanish Dwivedi
 */
@Entity
@Table(name = "Todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int todoID;

    //Setting up uni-directional Many-To-One with a user. Many Todo entites are mapped to a single User entity
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userFk;

    @Column(name = "title")
    private String title;

    @Column(name = "day")
    private Date day;


    public Todo() {
    }

    /**
     * Constructor for a Todo object.
     * @param title - A string descriping with this todo is about.
     * @param day - the day at which this todo is for.
     */
    public Todo(String title, Date day) {
        this.title = title;
        this.day = day;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public void setUserFk(User userFk) {
        this.userFk = userFk;
    }

    @Override
    public String toString() {
        return "Todo{" + "todoID=" + todoID + ", userFk=" + userFk.getUser_name() + ", title='" + title + '\'' + ", day=" + day + '}';
    }
}
