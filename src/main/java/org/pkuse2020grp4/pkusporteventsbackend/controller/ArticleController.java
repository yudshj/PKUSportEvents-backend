package org.pkuse2020grp4.pkusporteventsbackend.controller;

import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
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
    public Result getList(@RequestBody @Valid Filter filter){
        List<Article> list = articleService.getArticles(filter);
        if(list.size() == 0)
            return Result.buildFailResult("No target article found");
        else
            return Result.buildSuccessResult("Get articles", list);
    }

}
