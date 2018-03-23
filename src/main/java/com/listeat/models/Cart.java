package com.listeat.models;

import com.listeat.helpers.SqlDateAdapter;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;

@Entity
@Table(name = "carts")
@Data
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;

    @XmlJavaTypeAdapter(SqlDateAdapter.class)
    private Date purchase_time;
}
