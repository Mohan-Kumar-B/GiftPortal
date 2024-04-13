package com.example.springboot.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String giftName;
    

    @OneToMany(mappedBy = "gift", cascade = CascadeType.ALL)
    private List<User> users;

    // Constructors, getters, and setters
    public Gift() {
    }

    public Gift(String giftName) {
        this.giftName = giftName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getgiftName() {
        return giftName;
    }

    public void setgiftName(String giftName) {
        this.giftName = giftName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
