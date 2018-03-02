package com.listeat.models;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@Table(name="users")
@Data
@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.FIELD)
@lombok.EqualsAndHashCode(exclude={"glists"}) //Explanation at GList class
@lombok.ToString(exclude = "glists") //Explanation at GList class
public class User implements Serializable {
    @Id
    private int user_id;

    private String username;

    @Temporal(TemporalType.DATE)
    private Date last_login;

    @lombok.Getter(onMethod = @__( @JsonIgnore )) //Explanation at GList class
    @ManyToMany(mappedBy = "users")
    public List<GList> glists = new ArrayList<GList>();
}
