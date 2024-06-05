package com.ftn.sbnz.service.feature_auth.service.interf;


import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import com.ftn.sbnz.model.feature_auth.dtos.RegisterDTO;
import com.ftn.sbnz.model.feature_auth.dtos.UserDetailsDTO;
import com.ftn.sbnz.model.feature_auth.models.User;

public interface IUserService {
    User getByUsername(String username) throws Exception;
    User register(RegisterDTO dto) throws ResourceNotFoundException;
    List<UserDetailsDTO> getWorkers();
}
