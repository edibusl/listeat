package com.listeat.models;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

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
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    private String name;
    private String description;
}
