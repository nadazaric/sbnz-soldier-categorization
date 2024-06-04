package com.ftn.sbnz.service.feature_auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ftn.sbnz.model.feature_auth.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
