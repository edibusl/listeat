package com.listeat.models;

import javax.persistence.*;

@Entity
@Table(name = "glists_users", schema = "listeat", catalog = "")
@IdClass(GlistsUsersEntityPK.class)
public class GlistsUsersEntity {
    private int userId;
    private int glistId;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "glist_id")
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

        GlistsUsersEntity that = (GlistsUsersEntity) o;

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
