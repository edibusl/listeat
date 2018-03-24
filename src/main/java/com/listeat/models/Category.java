package com.listeat.models;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
@Table(name = "categories")
@XmlRootElement()
@lombok.Data
@lombok.EqualsAndHashCode(exclude={"products"}) //To prevent stack-overflow exception during commit due to circular dependencies
@lombok.ToString(of = "categody_id") //To prevent stack-overflow exception in any case when calling toString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    private String name;
    private String description;
}
