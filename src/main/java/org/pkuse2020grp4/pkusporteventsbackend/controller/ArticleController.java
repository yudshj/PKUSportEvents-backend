package org.pkuse2020grp4.pkusporteventsbackend.controller;

import org.pkuse2020grp4.pkusporteventsbackend.annotation.CheckedArticle;
import org.pkuse2020grp4.pkusporteventsbackend.annotation.CheckedTagIdList;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;
import org.pkuse2020grp4.pkusporteventsbackend.exception.ArticleIdNotValidException;
import org.pkuse2020grp4.pkusporteventsbackend.service.ArticleService;
import org.pkuse2020grp4.pkusporteventsbackend.service.TagService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @PostMapping("/api/article/get/{id}")
    public Result get(@PathVariable("id") int articleId) {
        Article article = articleService.getArticle(articleId);
        if (article == null)
            return Result.buildFailResult("Article not found");
        return Result.buildSuccessResult("success", article);
    }

    @PostMapping("/api/article/getall")
    public Result getAllArticles(@CheckedTagIdList List<Tag> tags){
        List<Article> list;
        if (tags == null) list = articleService.getAllArticles();
        else list = articleService.getArticlesByTagsFilter(tags);
        return Result.buildSuccessResult("Get all articles", list);
    }

    @PostMapping("/api/article/add")
    public Result addArticle(@CheckedArticle Article article) throws ArticleIdNotValidException {
        if (article.getArticleId() != null) {
            throw new ArticleIdNotValidException("Adding article request should NOT contain `articleId`!");
        }
        articleService.addOrModifyArticle(article);
        return Result.buildSuccessResult("Successfully added article.");
    }

    @PostMapping("/api/article/edit")
    public Result editArticle(@CheckedArticle Article article) throws ArticleIdNotValidException {
        if (article.getArticleId() == null) {
            throw new ArticleIdNotValidException("Editing article request should contain `articleId`!");
        }
        articleService.addOrModifyArticle(article);
        return Result.buildSuccessResult("Successfully edited article.");
    }

    @DeleteMapping("/api/article/delete/{id}")
    public Result delete(@PathVariable("id") int articleId){
        articleService.deleteById(articleId);
        return Result.buildSuccessResult(String.format("Deleted article %d", articleId));
    }

}
