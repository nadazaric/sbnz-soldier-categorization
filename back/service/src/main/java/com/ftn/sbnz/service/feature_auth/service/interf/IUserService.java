package com.ftn.sbnz.service.feature_auth.service.interf;

import com.ftn.sbnz.model.feature_auth.models.User;

public interface IUserService {
    User getByUsername(String username) throws Exception;
}
