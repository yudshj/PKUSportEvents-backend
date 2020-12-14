package org.pkuse2020grp4.pkusporteventsbackend.entity;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

/*
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "team")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
*/
/*@Entity*/
@Embeddable
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Team {
    /*@Id*/
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    /*@Column(name = "team_id")*/
    private int teamId;

    private String name;

    private int teamScore;
/*
    @OneToMany(mappedBy = "team")
    List<MatchTeam> teamMatches;
*/
}
