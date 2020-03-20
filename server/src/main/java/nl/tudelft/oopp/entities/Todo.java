package nl.tudelft.oopp.entities;

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

@Entity
@Table(name = "Todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int todoID;

    @Column(name = "title")
    private String title;

    //Setting up uni-directional Many-To-One with a user. Many Todo entites are mapped to a single User entity
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userFk;

    public Todo() {
    }

    /**
     * Constructor for a Todo object.
     * @param title - A string descriping with this todo is about.
     */
    public Todo(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserFk(User userFk) {
        this.userFk = userFk;
    }

}
