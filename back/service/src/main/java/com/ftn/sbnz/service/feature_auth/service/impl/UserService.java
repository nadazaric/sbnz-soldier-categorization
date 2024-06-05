package com.ftn.sbnz.service.feature_auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ftn.sbnz.model.feature_auth.dtos.RegisterDTO;
import com.ftn.sbnz.model.feature_auth.dtos.UserDetailsDTO;
import com.ftn.sbnz.model.feature_auth.models.Role;
import com.ftn.sbnz.model.feature_auth.models.User;
import com.ftn.sbnz.service.feature_auth.repository.RoleRepository;
import com.ftn.sbnz.service.feature_auth.repository.UserRepository;
import com.ftn.sbnz.service.feature_auth.service.interf.IUserService;

@Service
public class UserService implements IUserService{

    @Autowired UserRepository userRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @Override
    public User getByUsername(String username) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, username);
        return user.get();
    }

    @Override
    public User register(RegisterDTO dto) throws ResourceNotFoundException {
        boolean alreadyExist = alreadyExistWithUsername(dto.getUsername());
        if (alreadyExist) throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User with username %s already exist!", dto.getUsername()));
        User user = new User(
            dto.getName(),
            dto.getUsername(),
            passwordEncoder.encode(dto.getPassword()),
            roleRepository.findByName("WORKER")
        );
        return userRepository.save(user);
    }

    public boolean alreadyExistWithUsername(String username) {
        Optional<User> userEntity = userRepository.findByUsername(username);
        return userEntity.isPresent();
    }

    @Override
    public List<UserDetailsDTO> getWorkers() {
        List<User> users = userRepository.findAll();
        List<UserDetailsDTO> userDetailsDTOs = new ArrayList<>();
        for (User user : users) {
            if (((Role)user.getRoles().toArray()[0]).getName().equals("WORKER"))
                userDetailsDTOs.add(new UserDetailsDTO(user.getName(), user.getUsername()));
        }
        return userDetailsDTOs;
    }
    
}
