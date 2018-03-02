package com.listeat.models;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@Entity
@Table(name = "carts")
@Data
@XmlRootElement()
public class Cart {
    @Id
    private int cart_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp purchase_time;
}
