package com.listeat.models;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@Entity
@Table(name = "gitems")
@Data
@XmlRootElement()
public class GItem {
    @Id
    private int gitem_id;
    private Integer quantity;
    private Integer weight;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp created_time;
    private String comments;
}
