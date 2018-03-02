package com.listeat.models;

import javax.persistence.*;

@Entity
@Table(name = "glists", schema = "listeat", catalog = "")
public class GlistsEntity {
    private int glistId;
    private String subject;
    private String description;

    @Id
    @Column(name = "glist_id")
    public int getGlistId() {
        return glistId;
    }

    public void setGlistId(int glistId) {
        this.glistId = glistId;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlistsEntity that = (GlistsEntity) o;

        if (glistId != that.glistId) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = glistId;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
