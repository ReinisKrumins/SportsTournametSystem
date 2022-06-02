package com.StSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usermessages")
public class UserMessages {

    @Id
    private int id;

    @Column(name = "Name")
    private String name;


    @Column(name = "Email")
    private String email;

    @Column(name = "Subject")
    private String subject;


    @Column(name= "Message")
    private String message;

    public UserMessages() {
    }
    public UserMessages(String name, String email, String subject, String message) {
        this.setName(name);
        this.setEmail(email);
        this.setSubject(subject);
        this.setMessage(message);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name= name;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {this.email = email; }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //@Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", Name='" + getName() + '\'' +
                ", Email='" + getEmail() + '\'' +
                ", Subject='" + getSubject() + '\'' +
                ", Message='" + getMessage() + '\'' +
                '}';
    }

}
