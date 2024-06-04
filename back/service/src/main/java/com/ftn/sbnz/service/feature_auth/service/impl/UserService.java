package com.ftn.sbnz.service.feature_auth.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.feature_auth.models.User;
import com.ftn.sbnz.service.feature_auth.repository.UserRepository;
import com.ftn.sbnz.service.feature_auth.service.interf.IUserService;

@Service
public class UserService implements IUserService{

    @Autowired UserRepository userRepository;

    @Override
    public User getByUsername(String username) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) throw new Exception("No user with username: " + username);
        return user.get();
    }
    
}
