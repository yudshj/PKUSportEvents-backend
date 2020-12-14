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

import java.util.LinkedList;
import java.util.List;

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

    public List<Article> getArticles(List<Tag> filterTags){
        List<Article> articles;
        Sort sort = Sort.by(Sort.Direction.DESC, "articleId");
        articles = articleRepository.findArticlesByTagsIsIn(filterTags, sort);
        return articles;
    }

    public void addArticle(Article article){
        try {
            List<Tag> sendTags=article.getTags();
            List<Tag> tags=new LinkedList<>();

            for (Tag t:
                 sendTags) {
                int tagId=t.getTagId();
                Tag tagInBase=tagRepository.findTagByTagId(tagId);
                if(tagInBase==null)
                    throw new TagIdNotFoundException(tagId);
                tags.add(tagInBase);
            }

            // for (Tag tag:
            //      tags) {
            //     tag.getArticles().add(article);
            //     tagRepository.save(tag);
            // }

            articleRepository.save(article);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        articleRepository.deleteById(id);
    }
}
