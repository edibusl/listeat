package com.listeat.models;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "products")
@Data
@XmlRootElement()
public class Product {
    @Id
    private int product_id;
    private String name;
    private String description;
    private String image_path;
}
