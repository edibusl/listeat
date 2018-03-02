package com.listeat.models;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "categories")
@Data
@XmlRootElement()
public class Category {
    @Id
    private int category_id;
    private String name;
    private String description;
}
