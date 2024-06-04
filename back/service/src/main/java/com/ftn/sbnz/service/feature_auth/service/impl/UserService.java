package com.ftn.sbnz.service.feature_auth.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ftn.sbnz.model.feature_auth.models.User;
import com.ftn.sbnz.service.feature_auth.repository.UserRepository;
import com.ftn.sbnz.service.feature_auth.service.interf.IUserService;

@Service
public class UserService implements IUserService{

    @Autowired UserRepository userRepository;

    @Override
    public User getByUsername(String username) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, username);
        return user.get();
    }
    
}
