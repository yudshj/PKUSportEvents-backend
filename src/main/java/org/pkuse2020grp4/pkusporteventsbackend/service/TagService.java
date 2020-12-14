package org.pkuse2020grp4.pkusporteventsbackend.service;

import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;
import org.pkuse2020grp4.pkusporteventsbackend.repo.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public List<Tag> getAllTags(){
        List<Tag> tags;
        Sort sort=Sort.by(Sort.Direction.ASC,"tagId");
        tags=tagRepository.findAll(sort);
        return tags;
    }

    public void addTag(Tag tag) {
        tagRepository.save(tag);
    }
}
