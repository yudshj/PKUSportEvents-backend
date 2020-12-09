package org.pkuse2020grp4.pkusporteventsbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.pkuse2020grp4.pkusporteventsbackend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findUserByUsername(String username);
    User findUserByUserId(Integer userId);
    boolean existsUserByUsername(String username);
}
