package org.pkuse2020grp4.pkusporteventsbackend.service;

import org.pkuse2020grp4.pkusporteventsbackend.dto.ApplyFormDTO;
import org.pkuse2020grp4.pkusporteventsbackend.entity.ApplyForm;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Article;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;
import org.pkuse2020grp4.pkusporteventsbackend.repo.ApplyFormRepository;
import org.pkuse2020grp4.pkusporteventsbackend.repo.ArticleRepository;
import org.pkuse2020grp4.pkusporteventsbackend.repo.TagRepository;
import org.pkuse2020grp4.pkusporteventsbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplyFormService {
    @Autowired
    ApplyFormRepository applyFormRepository;

    public void saveApplyForm(ApplyFormDTO applyFormDTO) {
        ApplyForm applyForm = new ApplyForm();
        applyForm.setPermission(applyFormDTO.getPermission());
        applyForm.setApplyDate(applyFormDTO.getApplyDate());
        applyForm.setUserId(applyFormDTO.getUserId());
        applyFormRepository.save(applyForm);
    }

    public List<ApplyForm> getAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "applyId");
        return applyFormRepository.findAll(sort);
    }

    public ApplyForm getAndRemove(int applyId) {
        Optional<ApplyForm> tmp = applyFormRepository.findById(applyId);
        if (tmp.isPresent()) {
            ApplyForm applyForm = tmp.get();
            applyFormRepository.deleteById(applyId);
            return applyForm;
        }
        return null;
    }
}
