package com.listeat.models;

import lombok.AccessLevel;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@lombok.Data
@lombok.EqualsAndHashCode(exclude={"gitems"}) //To prevent stack-overflow exception during commit due to circular dependencies
@lombok.ToString(of = "product_id") //To prevent stack-overflow exception in any case when calling toString
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    private String name;
    private String description;
    private String image_path;

    @JsonIgnore @lombok.Getter(onMethod = @__(@JsonIgnore)) //To ignore this field when serializing to Json (A jackson's JsonIgnore field will be put on the setter that's generated by lombok)
    @OneToMany(mappedBy = "product", fetch=FetchType.LAZY)
    public List<GItem> gitems;

    @ManyToOne(fetch=FetchType.EAGER) //Eager load because a Category object is a must when describing a Product (it's always needed)
    @JoinColumn(name="category_id")
    private Category category;

    @JsonIgnore @lombok.Getter(onMethod = @__(@JsonIgnore))
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="glist_id")
    private GList glist;

    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    @Transient
    private Long glist_id;
    public Long getGlist_id() {
        return glist_id;
    }
    public void setGlist_id(Long glist_id) {
        this.glist_id = glist_id;
    }

    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    @Transient
    private Long category_id;
    public Long getCategory_id() {
        return category_id;
    }
    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
}
