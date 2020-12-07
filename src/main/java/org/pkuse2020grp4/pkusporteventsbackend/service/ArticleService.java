package org.pkuse2020grp4.pkusporteventsbackend.service;

import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.repo.ArticleRepository;
import org.pkuse2020grp4.pkusporteventsbackend.repo.UserRepository;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    public List<Article> getAllArticles() {
        List<Article> articles;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        articles = articleRepository.findAll(sort);
        return articles;
    }

    public List<Article> getArticles(Filter filter){
        List<Article> list;
        if(filter.getDayDistance() != -1){
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, - filter.getDayDistance());
            list = articleRepository.findArticlesByReleaseDateIsAfter(cal.getTime());
        }
        else{
            list = articleRepository.findAll();
        }
        if(filter.getDateSequence() == Filter.Sequence.Reverse){
            list.sort(new Comparator<Article>() {
                @Override
                public int compare(Article o1, Article o2) {
                    return - o1.getReleaseDate().compareTo(o2.getReleaseDate());
                }
            });
        }
        if(filter.getNameSequence() == Filter.Sequence.Positive){
            list.sort(new Comparator<Article>() {
                @Override
                public int compare(Article o1, Article o2) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            });
        }
        else{
            list.sort(new Comparator<Article>() {
                @Override
                public int compare(Article o1, Article o2) {
                    return - o1.getTitle().compareTo(o2.getTitle());
                }
            });
        }
        return list;
    }

    public void addOrUpdateArticle(Article article){
        articleRepository.save(article);
    }

    public void deleteById(int id) {
        articleRepository.deleteById(id);
    }
}
