package com.listeat.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.listeat.helpers.CustomDateDeserializer;
import com.listeat.helpers.CustomDateSerializer;
import lombok.AccessLevel;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@Table(name="users")
@lombok.Data
@lombok.EqualsAndHashCode(exclude={"glists", "gitems"}) //Explanation at GList class
@lombok.ToString(exclude = "glists") //Explanation at GList class
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String username;
    private String name;
    private String profile_image;

    //We disable lombok here since we need to set a custom field with vars (JsonSerializable) on the getter
    @lombok.Getter(AccessLevel.NONE)
    @lombok.Setter(AccessLevel.NONE)
    private Date last_login;
    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getLast_login() {return this.last_login;}
    @JsonDeserialize(using = CustomDateDeserializer.class)
    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    @lombok.Getter(onMethod = @__(@JsonIgnore))
    public List<GList> glists = new ArrayList<>();

    @JsonIgnore @lombok.Getter(onMethod = @__(@JsonIgnore))
    @OneToMany(mappedBy = "user")
    public List<GItem> gitems;
}
