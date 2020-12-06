package org.pkuse2020grp4.pkusporteventsbackend.service;

import org.pkuse2020grp4.pkusporteventsbackend.entity.User;
import org.pkuse2020grp4.pkusporteventsbackend.repo.UserRepository;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Result;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Salt;
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

    public Result registerUser(User user){
        if (userRepository.existsUserByUsername(user.getUsername()))
            return new Result(1, "Dulicated username", null);
        if (user.getPassword().length() < 6)
            return new Result(2, "Too simple password", null);
        user.setUid(UID.genNewUID());
        user.setSalt(Salt.generateSalt());
        user.setPassword(Salt.salty(user.getPassword(), user.getSalt()));
        userRepository.save(user);
        return new Result(0, "Dulicated username", user.getSalt());
    }
}
