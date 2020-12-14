package org.pkuse2020grp4.pkusporteventsbackend.controller;

import org.pkuse2020grp4.pkusporteventsbackend.annotation.CheckedArticle;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;
import org.pkuse2020grp4.pkusporteventsbackend.service.ArticleService;
import org.pkuse2020grp4.pkusporteventsbackend.service.TagService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @PostMapping("/api/article/get/{id}")
    public Result get(@PathVariable("id") int articleId) {
        return Result.buildSuccessResult("success", articleId + "data");
    }

    @PostMapping("/api/article/getlist")
    public Result getArticlesWithFilter(@RequestBody @Valid List<Tag> filterTags){
        List<Article> list = articleService.getArticles(filterTags);
        return Result.buildSuccessResult("Get articles with filter", list);
    }

    @PostMapping("/api/article/getall")
    public Result getAllArticles(){
        List<Article> list = articleService.getAllArticles();
        return Result.buildSuccessResult("Get all articles", list);
    }

    @PostMapping("/api/article/add")
    public Result addArticle(@CheckedArticle Article article){
        articleService.addArticle(article);
        return Result.buildSuccessResult("Successfully added article.");
    }

    @PostMapping("/api/article/edit")
    public Result editArticle(){
        List<Tag> tags = tagService.getAllTags();
        return Result.buildSuccessResult("Got all tags.",tags);
    }

}
