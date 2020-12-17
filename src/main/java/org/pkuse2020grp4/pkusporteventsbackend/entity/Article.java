package org.pkuse2020grp4.pkusporteventsbackend.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableList;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "article")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Integer articleId;

    private Integer authorId;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseDate;

    private String title;

    @Lob
    private String markdownContent;

    @Lob
    private String htmlContent;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "r_article_tag",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    @JsonIgnore
    private List<Tag> tags;

    @JSONField(serialize = false)
    public List<Tag> getTags() {
        return tags;
    }

    @JSONField(name = "tagIds")
    public List<Integer> getTagIds() {
        ImmutableList.Builder<Integer> builder = new ImmutableList.Builder<>();
        for (Tag tag: tags) {
            builder.add(tag.getTagId());
        }
        return builder.build();
    }
}
