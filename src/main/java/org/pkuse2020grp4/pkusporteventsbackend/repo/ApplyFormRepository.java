package org.pkuse2020grp4.pkusporteventsbackend.repo;

import org.pkuse2020grp4.pkusporteventsbackend.entity.ApplyForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyFormRepository extends JpaRepository<ApplyForm, Integer> {
}
