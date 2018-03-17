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
@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.FIELD)
@lombok.Data
@lombok.EqualsAndHashCode(exclude={"glists"}) //Explanation at GList class
@lombok.ToString(exclude = "glists") //Explanation at GList class
public class User implements Serializable {
    @Id
    private int user_id;

    private String username;

    @XmlJavaTypeAdapter(SqlDateAdapter.class)
    private Date last_login;

    @lombok.Getter(onMethod = @__(@JsonIgnore)) //Explanation at GList class
    @ManyToMany(mappedBy = "users")
    @XmlTransient
    public List<GList> glists = new ArrayList<GList>();
}
