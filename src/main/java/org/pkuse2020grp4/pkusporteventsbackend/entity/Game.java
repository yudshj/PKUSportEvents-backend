package org.pkuse2020grp4.pkusporteventsbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "game")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
/*@Inheritance(strategy = InheritanceType.SINGLE_TABLE)*/
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int gameId;

    private int mark;

    private String title;

    @Lob
    private String content;

    private int homeId;

    private String homeName;

    private int homeScore;

    private int awayId;

    private String awayName;

    private int awayScore;

/*    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "game_team", joinColumns = @JoinColumn(name = "game_id"))
    private Set<Team> teams=new HashSet<>();*/
}

