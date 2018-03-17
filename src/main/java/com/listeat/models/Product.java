package com.listeat.models;

import lombok.AccessLevel;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
@Table(name = "products")
@XmlRootElement()
@lombok.Data
@lombok.EqualsAndHashCode(exclude={"gitems"}) //To prevent stack-overflow exception during commit due to circular dependencies
@lombok.ToString(of = "product_id") //To prevent stack-overflow exception in any case when calling toString
public class Product {
    @Id
    private int product_id;
    private String name;
    private String description;
    private String image_path;

    //To ignore this field when serializing to Json (A jackson's JsonIgnore field will be put on the setter that's generated by lombok)
    @lombok.Getter(onMethod = @__(@JsonIgnore))
    @XmlTransient ///To prevent stack-overflow exception during serialization to XML due to circular dependencies
    @OneToMany(mappedBy = "product")
    public List<GItem> gitems;

    @lombok.Getter(onMethod = @__(@JsonIgnore))
    @XmlTransient
    @ManyToOne(fetch=FetchType.EAGER) //Eager load because a Category object is a must when describing a Product (it's always needed)
    @JoinColumn(name="category_id")
    private Category category;

    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    @Transient
    /**
     * This field is used to represent the Category of this Product during serialization.
     * More explanation in the docs of GItem.product_obj field
     */
    private Category category_obj;
    public void setCategory_obj(Category obj) {
        obj.setProducts(null);
        this.category_obj = obj;
    }
}
