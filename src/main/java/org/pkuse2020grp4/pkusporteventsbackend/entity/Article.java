package org.pkuse2020grp4.pkusporteventsbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "article")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
}
