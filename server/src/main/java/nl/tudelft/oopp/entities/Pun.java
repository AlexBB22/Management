package nl.tudelft.oopp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "puns")

public class Pun{
    @Id
    @Column(name = "text")
    public String pun;

    public Pun() {
    }

    public Pun(String pun) {
        this.pun = pun;
    }

    public String getPun() {
        return pun;
    }

    public void setPun(String pun) {
        this.pun = pun;
    }
}
