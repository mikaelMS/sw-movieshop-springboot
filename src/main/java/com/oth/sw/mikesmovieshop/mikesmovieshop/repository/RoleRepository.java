package com.oth.sw.mikesmovieshop.mikesmovieshop.repository;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.Role;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByRoleId (int id);
}
