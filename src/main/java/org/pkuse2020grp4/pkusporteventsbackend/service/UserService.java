package org.pkuse2020grp4.pkusporteventsbackend.service;

import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.exception.PasswordNotValidException;
import org.pkuse2020grp4.pkusporteventsbackend.exception.UserNotFoundException;
import org.pkuse2020grp4.pkusporteventsbackend.repo.UserRepository;
import org.pkuse2020grp4.pkusporteventsbackend.utils.UID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public int getUserId(User user) throws Exception {
        User findResult = userRepository.findUserByUsername(user.getUsername());
        if (findResult == null)
            throw new UserNotFoundException(user.getUsername());
        if (!findResult.getPassword().equals(user.getPassword()))
            throw new PasswordNotValidException();
        return findResult.getUserId();
    }

    public Integer registerUser(User user){
        if (userRepository.existsUserByUsername(user.getUsername()))
            return 1;
        if (user.getPassword().length() < 6)
            return 2;
        user.setUserId(UID.genNewUID());
        // user.setSalt(Salt.generateSalt(0));
        // user.setPassword(Salt.salty(user.getPassword(), user.getSalt(), 0));
        userRepository.save(user);
        return 0;
    }

    public Boolean existUser(String username){
        return userRepository.existsUserByUsername(username);
    }

    // public String getSalt(String username){
    //     return userRepository.findUserByUsername(username).getSalt();
    // }
}
