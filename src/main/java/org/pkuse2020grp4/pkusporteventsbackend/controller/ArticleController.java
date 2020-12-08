package org.pkuse2020grp4.pkusporteventsbackend.controller;

import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;
import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.repo.ArticleRepository;
import org.pkuse2020grp4.pkusporteventsbackend.service.ArticleService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Filter;
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

    private ArticleService articleService;

    @PostMapping("/api/article/get/{id}")
    public Result get(@PathVariable("id") int articleId) {
        return Result.buildSuccessResult("success", articleId + "data");
    }

    @PostMapping("/api/article/getlist")
    public Result getList(@RequestBody @Valid List<Tag> filterTags){
        List<Article> list = articleService.getArticles(filterTags);
        return Result.buildSuccessResult("Get articles", list);
    }

    @PostMapping("/api/article/add")
    public Result addArticle(@RequestBody @Valid Article article){
        articleService.addArticle(article);
        return Result.buildSuccessResult("Successfully added article.");
    }

}
