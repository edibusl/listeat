package com.listeat.models;

import com.listeat.helpers.CustomDateDeserializer;
import com.listeat.helpers.CustomDateSerializer;
import lombok.AccessLevel;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "gitems")
@lombok.Data
@lombok.EqualsAndHashCode(exclude={"glist", "product", "user"}) //To prevent stack-overflow exception during commit due to circular dependencies
@lombok.ToString(of = "gitem_id") //To prevent stack-overflow exception in any case when calling toString
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gitem_id;

    private Integer quantity;
    private Integer weight;
    private Boolean is_checked = false;

    //We disable lombok here since we need to set a custom field with vars (JsonSerializable) on the getter
    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    private Date created_date;
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreated_date() {return this.created_date;}
    @JsonDeserialize(using = CustomDateDeserializer.class)
    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    private String comments;

    @JsonIgnore @lombok.Getter(onMethod = @__(@JsonIgnore)) //To ignore this field when serializing to Json (A jackson's JsonIgnore field will be put on the setter that's generated by lombok)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="glist_id")
    private GList glist;

    @JsonIgnore @lombok.Getter(onMethod = @__(@JsonIgnore))
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @JsonIgnore @lombok.Getter(onMethod = @__(@JsonIgnore))
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;

    @JsonIgnore //To prevent stack overflow
    @ManyToOne(fetch=FetchType.EAGER) //Eager load because a Product object is a must when describing a GItem (it's always needed)
    @JoinColumn(name="product_id")
    private Product product;

    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    @Transient
    private Long user_id;
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    @Transient
    private Long product_id;
    public Long getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

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
    private Long cart_id;
    public Long getCart_id() {
        return cart_id;
    }
    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }
}
