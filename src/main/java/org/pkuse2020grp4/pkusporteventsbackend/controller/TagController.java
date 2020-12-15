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
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/api/tag/add")
    public Result addArticle(@RequestBody Tag tag){
        tagService.addTag(tag);
        return Result.buildSuccessResult("Successfully added tag.");
    }

    @PostMapping("/api/tag/getall")
    public Result getAllTags(){
        List<Tag> tags = tagService.getAllTags();
        return Result.buildSuccessResult("Got all tags.",tags);
    }

}
