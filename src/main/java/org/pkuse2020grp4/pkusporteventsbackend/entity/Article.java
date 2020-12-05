package org.pkuse2020grp4.pkusporteventsbackend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "author")
    private Integer author_id;

    @Column(name = "date")
    private Date release;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
}
