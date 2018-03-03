package com.listeat.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "glists")
@XmlRootElement(name="GList")
@XmlAccessorType(XmlAccessType.FIELD)
@lombok.Data
@lombok.EqualsAndHashCode(exclude={"users"}) //To prevent stack-overflow exception during commit due to circular dependencies
@lombok.ToString(exclude = "users") //To prevent stack-overflow exception in any case when calling toString
public class GList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long glist_id;
    private String subject;
    private String description;

    //To ignore this field when serializing to Json (A jackson's JsonIgnore field will be put on the setter that's generated by lombok)
    @lombok.Getter(onMethod = @__(@JsonIgnore))
    @XmlTransient ///To prevent stack-overflow exception during serialization to XML due to circular dependencies
    @ManyToMany
    @JoinTable(name = "glists_users",
            joinColumns = @JoinColumn(name = "glist_id", referencedColumnName = "glist_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    )
    public List<User> users = new ArrayList<User>();
}
