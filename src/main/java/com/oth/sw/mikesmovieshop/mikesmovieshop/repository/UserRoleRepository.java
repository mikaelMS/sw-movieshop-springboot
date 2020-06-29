package com.oth.sw.mikesmovieshop.mikesmovieshop.repository;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.Role;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    UserRole save(UserRole id);
}
