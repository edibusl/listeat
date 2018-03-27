package com.listeat.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.listeat.helpers.SqlDateAdapter;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="users")
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
@lombok.Data
@lombok.EqualsAndHashCode(exclude={"glists", "gitems"}) //Explanation at GList class
@lombok.ToString(exclude = "glists") //Explanation at GList class
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String username;

    @XmlJavaTypeAdapter(SqlDateAdapter.class)
    private Date last_login;

    @ManyToMany(mappedBy = "users")
    @XmlTransient @lombok.Getter(onMethod = @__(@XmlTransient))
    public List<GList> glists = new ArrayList<>();

    @XmlTransient @lombok.Getter(onMethod = @__(@XmlTransient))
    @OneToMany(mappedBy = "user")
    public List<GItem> gitems;
}
