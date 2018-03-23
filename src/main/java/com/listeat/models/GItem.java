package com.listeat.models;

import com.listeat.helpers.SqlDateAdapter;
import lombok.AccessLevel;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;

@Entity
@Table(name = "gitems")
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
@lombok.Data
@lombok.EqualsAndHashCode(exclude={"glist", "product", "user"}) //To prevent stack-overflow exception during commit due to circular dependencies
@lombok.ToString(of = "gitem_id") //To prevent stack-overflow exception in any case when calling toString
public class GItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gitem_id;

    private Integer quantity;
    private Integer weight;

    @XmlJavaTypeAdapter(SqlDateAdapter.class)
    private Date created_time;

    private String comments;

    //To ignore this field when serializing to Json (A jackson's JsonIgnore field will be put on the setter that's generated by lombok)
    @lombok.Getter(onMethod = @__(@JsonIgnore))
    @XmlTransient //To prevent stack-overflow exception during serialization to XML due to circular dependencies
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="glist_id")
    private GList glist;

    @lombok.Getter(onMethod = @__(@JsonIgnore))
    @XmlTransient
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;

    @lombok.Getter(onMethod = @__(@JsonIgnore))
    @XmlTransient
    @ManyToOne(fetch=FetchType.EAGER) //Eager load because a Product object is a must when describing a GItem (it's always needed)
    @JoinColumn(name="product_id")
    private Product product;

    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    @Transient
    /**
     * This field is used to represent the Product of this GItem during serialization.
     * Explanation -
     * All the FK mapping fields are marked with XmlTransient and JsonIgnore in order to prevent circular dependencies
     * during serialization to JSON.
     * But we do want the Product field to be serialized, so we provide another setter
     * to set a non-DB field which will be serialized. When setting this field, we set the GItems field of the Product
     * to null in order to prevent circular dependencies during the serialization of this field.
     */
    private Product product_obj;
    public void setProduct_obj(Product obj) {
        //To prevent circular dependencies
        obj.setGitems(null);

        //Do the same also for the category object
        Category category = obj.getCategory();
        if(category != null) {
            obj.setCategory_obj(category);
        }

        this.product_obj = obj;
    }

    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    @Transient
    private Long user_id;
    public Long getUserId() {
        return user_id;
    }
    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    @Transient
    private Long product_id;
    public Long getProductId() {
        return product_id;
    }
    public void setProductId(Long product_id) {
        this.product_id = product_id;
    }

    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    @Transient
    private Long glist_id;
    public Long getGlistId() {
        return glist_id;
    }
    public void setGlistId(Long glist_id) {
        this.glist_id = glist_id;
    }

    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    @Transient
    private Long cart_id;
    public Long getCartId() {
        return cart_id;
    }
    public void setCartId(Long cart_id) {
        this.cart_id = cart_id;
    }
}
