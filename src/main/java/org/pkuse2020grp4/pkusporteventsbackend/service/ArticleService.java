package org.pkuse2020grp4.pkusporteventsbackend.service;

import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;
import org.pkuse2020grp4.pkusporteventsbackend.exception.TagIdNotFoundException;
import org.pkuse2020grp4.pkusporteventsbackend.repo.ArticleRepository;
import org.pkuse2020grp4.pkusporteventsbackend.repo.TagRepository;
import org.pkuse2020grp4.pkusporteventsbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    public List<Article> getAllArticles() {
        List<Article> articles;
        Sort sort = Sort.by(Sort.Direction.DESC, "articleId");
        articles = articleRepository.findAll(sort);
        return articles;
    }

    public List<Article> getArticlesByTagsFilter(List<Tag> filterTags){
        List<Article> articles;
        Sort sort = Sort.by(Sort.Direction.DESC, "articleId");
        articles = articleRepository.findArticlesByTagsIn(filterTags, sort);
        Set<Article> uniquedArticles=new HashSet<>(articles);
/*
        for (Article a:
                uniquedArticles) {
            System.out.println(a.getArticleId());
        }
*/
        return new LinkedList<>(uniquedArticles);
    }

    public void addOrModifyArticle(Article article){
        articleRepository.save(article);
    }

    public void deleteById(int id) {
        articleRepository.deleteById(id);
    }

    public Article getArticle(int id) {
        return articleRepository.findArticleByArticleId(id);
    }
}
