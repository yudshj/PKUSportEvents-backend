package org.pkuse2020grp4.pkusporteventsbackend.service;

import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;
import org.pkuse2020grp4.pkusporteventsbackend.repo.ArticleRepository;
import org.pkuse2020grp4.pkusporteventsbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    public List<Article> getAllArticles() {
        List<Article> articles;
        Sort sort = Sort.by(Sort.Direction.DESC, "articleId");
        articles = articleRepository.findAll(sort);
        return articles;
    }

    public List<Article> getArticles(List<Tag> filterTags){
        List<Article> articles;
        Sort sort = Sort.by(Sort.Direction.DESC, "articleId");
        articles = articleRepository.findArticlesByTagsIsIn(filterTags, sort);
        return articles;
    }

    public void addArticle(Article article){
        articleRepository.save(article);
    }

    public void deleteById(int id) {
        articleRepository.deleteById(id);
    }
}
