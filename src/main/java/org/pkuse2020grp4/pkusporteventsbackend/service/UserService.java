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

    public Boolean checkUser(String username, String password){
        if(userRepository.findUserByUsername(username).getPassword().equals(password))
            return true;
        else
            return false;
    }

    public int registerUser(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        user.setUid(UID.genNewUID());
        /*
            user.setSalt(genSalt());
            user.setPassword(salty(password, user.getSalt()));
         */
        userRepository.save(user);
        return 0;
    }
}
