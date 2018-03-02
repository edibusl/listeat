package com.listeat.models;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="user_id")
    private int UserId;

    @Column(name="username")
    private String Username;

    @Column(name="last_login")
    @Temporal(TemporalType.DATE)
    private Date LastLogin;


    public int getUserId() {
        return this.UserId;
    }
    public void setUserId(int userId) {
        this.UserId = userId;
    }

    public String getUsername() {
        return this.Username;
    }
    public void setUsername(String username) {
        this.Username = username;
    }

    public Date getLastLogin() {
        return this.LastLogin;
    }
    public void setLastLogin(Date lastLogin) {
        this.LastLogin = lastLogin;
    }
}
