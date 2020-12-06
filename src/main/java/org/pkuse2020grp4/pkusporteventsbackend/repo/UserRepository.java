package org.pkuse2020grp4.pkusporteventsbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.pkuse2020grp4.pkusporteventsbackend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findUserByUsername(String username);
    User findUserByUid(Integer uid);
    boolean existsUserByUsername(String username);
}
