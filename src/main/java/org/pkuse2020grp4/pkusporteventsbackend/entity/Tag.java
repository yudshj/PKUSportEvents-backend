package org.pkuse2020grp4.pkusporteventsbackend.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tag")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int tagId;

    private String name;

    @ManyToMany(mappedBy = "tags",fetch = FetchType.EAGER)
    @JSONField(serialize=false)
    @JsonIgnore
    Set<Article> articles=new HashSet<>();
}
