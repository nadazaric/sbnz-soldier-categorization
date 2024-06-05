package com.ftn.sbnz.service.security;

// import com.aml.AML.Data.Model.UserEntity;
// import com.aml.AML.Data.Jpas.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.feature_auth.models.User;
import com.ftn.sbnz.service.feature_auth.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with this username: " + username));
        Set<GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
//        if (userEntity instanceof AdminEntity) {
//            return org.springframework.security.core.userdetails.User.withUsername(userEntity.getUsername()).password(userEntity.getPassword()).roles("ADMIN").build();
//        }

        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(), userEntity.getPassword(), authorities);
        // TODO This is just example - Change this to reflect our use
//        return org.springframework.security.core.userdetails.User.withUsername(userEntity.getUsername()).password(userEntity.getPassword()).roles("USER").build();
    }
}
