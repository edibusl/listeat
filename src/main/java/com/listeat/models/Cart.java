package com.listeat.models;

import com.listeat.helpers.SqlDateAdapter;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;

    private Long glist_id;

    @XmlJavaTypeAdapter(SqlDateAdapter.class)
    private Date purchase_time;

    //To ignore this field when serializing to Json (A jackson's JsonIgnore field will be put on the setter that's generated by lombok)
    @JsonIgnore @lombok.Getter(onMethod = @__(@JsonIgnore))
    @OneToMany(mappedBy = "cart")
    public List<GItem> gitems;
}
