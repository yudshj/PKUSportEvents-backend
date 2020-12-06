package org.pkuse2020grp4.pkusporteventsbackend.service;

import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.repo.UserRepository;
import org.pkuse2020grp4.pkusporteventsbackend.utils.UID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public int checkUser(User user){
        User findResult = userRepository.findUserByUsername(user.getUsername());
        if (findResult == null)
            return 1;
        if (!findResult.getPassword().equals(user.getPassword()))
            return 2;
        return 0;
    }

    public int registerUser(User user){
        if (userRepository.existsUserByUsername(user.getUsername()))
            return 1;
        if (user.getPassword().length() < 6)
            return 2;
        user.setUid(UID.genNewUID());
        /*
            user.setSalt(genSalt());
            user.setPassword(salty(password, user.getSalt()));
         */
        userRepository.save(user);
        return 0;
    }
}
