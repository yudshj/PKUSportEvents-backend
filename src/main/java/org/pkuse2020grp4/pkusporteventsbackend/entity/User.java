package org.pkuse2020grp4.pkusporteventsbackend.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uid;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false)
    private String username;

    /*
    @Column(name = "email")
    private String email;

    @Column(name = "salt")
    private String salt;
     */

    public User(String username, String password){this.username = username; this.password = password; };
    public User(){}

    public Integer getUid(){
        return this.uid;
    }
    public void setUid(Integer uid){
        this.uid = uid;
    }
    /*
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getSalt() { return salt; }
    public void setSalt(String salt) { this.salt = salt; }

     */

    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }

    public String getUsername() { return this.username; }
    public void setUserName(String username) { this.username = username; }

}
