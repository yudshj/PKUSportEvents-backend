package org.pkuse2020grp4.pkusporteventsbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

import org.pkuse2020grp4.pkusporteventsbackend.perm.perm;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
@ToString
@Transactional
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    private String username;

    private String password;

    private int permission = perm.DEFAULT;

    @Lob
    private String iconUrl;

    @Lob
    private String signature;

    // private String salt;

    // @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @JoinTable(name = "r_user_tag",
    //         joinColumns = {@JoinColumn(name = "user_id")},
    //         inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    // private List<Tag> interestTags;

    public User(String username, String password){
        this.password = password;
        this.username = username;
    }

}
