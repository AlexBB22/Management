package nl.tudelft.oopp.entities;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int clientId;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "UserPassword")
    private String userPassword;

    public ClientEntity() {}

    public ClientEntity(int clientId, String userName, String userPassword) {
        this.clientId = clientId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getClientId() {
        return clientId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
