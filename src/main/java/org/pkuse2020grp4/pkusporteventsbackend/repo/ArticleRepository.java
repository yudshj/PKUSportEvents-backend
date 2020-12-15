package org.pkuse2020grp4.pkusporteventsbackend.repo;

import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNullApi;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findArticlesByAuthorId(Integer id);
    List<Article> findArticlesByReleaseDateIsAfter(Date date);
    List<Article> findArticlesByTagsIn(List<Tag> tags, Sort sort);
    Article findArticleByArticleId(int articleId);
}
