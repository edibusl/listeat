package com.listeat.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class GlistsUsersEntityPK implements Serializable {
    private int userId;
    private int glistId;

    @Column(name = "user_id")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "glist_id")
    @Id
    public int getGlistId() {
        return glistId;
    }

    public void setGlistId(int glistId) {
        this.glistId = glistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlistsUsersEntityPK that = (GlistsUsersEntityPK) o;

        if (userId != that.userId) return false;
        if (glistId != that.glistId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + glistId;
        return result;
    }
}
