package com.ftn.sbnz.service.feature_auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.ftn.sbnz.model.feature_auth.dtos.LoginUserDTO;
import com.ftn.sbnz.model.feature_auth.dtos.RegisterDTO;
import com.ftn.sbnz.model.feature_auth.dtos.UserCredentialsDTO;
import com.ftn.sbnz.model.feature_auth.dtos.UserDetailsDTO;
import com.ftn.sbnz.model.feature_auth.models.User;
import com.ftn.sbnz.service.feature_auth.service.interf.IUserService;
import com.ftn.sbnz.service.security.jwt.JwtTokenUtil;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired private JwtTokenUtil jwtTokenUtil;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired IUserService userService;

    @PostMapping("/login")
    public LoginUserDTO login(@RequestBody @Valid UserCredentialsDTO userCredentials) throws Exception {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(userCredentials.getUsername(),
                userCredentials.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        User userEntity = userService.getByUsername(userCredentials.getUsername());

        String token = jwtTokenUtil.generateToken(userEntity.getUsername(), sc.getAuthentication().getAuthorities().toArray()[0].toString());
        String refreshToken = jwtTokenUtil.generateRefreshToken(userEntity.getUsername(), sc.getAuthentication().getAuthorities().toArray()[0].toString());

        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setAccessToken(token);
        loginUserDTO.setRefreshToken(refreshToken);
        
        return loginUserDTO;
    }

    @PostMapping(value = "/refreshToken")
    public LoginUserDTO refreshToken(@Valid @RequestBody LoginUserDTO dto) {
        if (dto.getRefreshToken() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Refresh token missing");
        }
        if (jwtTokenUtil.isTokenExpired(dto.getRefreshToken()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Your refresh token has expired, please log in again");

        String newJwt = jwtTokenUtil.generateToken(jwtTokenUtil.getUsernameFromToken(dto.getRefreshToken()), jwtTokenUtil.getRoleFromToken(dto.getRefreshToken()));
        dto.setAccessToken(newJwt);
        String newRefreshToken = jwtTokenUtil.generateRefreshToken(jwtTokenUtil.getUsernameFromToken(dto.getRefreshToken()), jwtTokenUtil.getRoleFromToken(dto.getRefreshToken()));
        dto.setRefreshToken(newRefreshToken);
        return dto;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetailsDTO> register(@RequestBody RegisterDTO dto) throws ResourceNotFoundException {
        User user = this.userService.register(dto);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(user.getName(), user.getUsername());
        return new ResponseEntity<>(userDetailsDTO, HttpStatus.OK);
    }
}
