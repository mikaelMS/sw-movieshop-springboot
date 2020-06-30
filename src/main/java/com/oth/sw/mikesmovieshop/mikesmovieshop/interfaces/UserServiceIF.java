package com.oth.sw.mikesmovieshop.mikesmovieshop.interfaces;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;

import java.util.Optional;

public interface UserServiceIF {
    User saveUser(User user);

    User findUser(String email);
}
