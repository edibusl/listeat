package com.listeat.models;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@lombok.Data
@lombok.EqualsAndHashCode(exclude={"products"}) //To prevent stack-overflow exception during commit due to circular dependencies
@lombok.ToString(of = "category_id") //To prevent stack-overflow exception in any case when calling toString
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    private String name;
    private String description;
}
