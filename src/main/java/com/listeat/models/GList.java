package com.listeat.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "glists")
@XmlRootElement()
public class GList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "glist_id")
    private long glist_id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    public long getGlistId() {
        return glist_id;
    }

    public void setGlistId(long glist_id) {
        this.glist_id = glist_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
