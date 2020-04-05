package com.oth.sw.mikesmovieshop.mikesmovieshop.service;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;
import com.oth.sw.mikesmovieshop.mikesmovieshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Qualifier("labresources")
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//        User user = userRepository.findById(Long.parseLong(id))
//                .orElseThrow(() -> {
//                            throw new UsernameNotFoundException("User with nr. " + id + " doesn't exist");
//                        }
//                );
//        return user;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("hier0 " + email);
        User user = userRepository.findByEmail(email);
        System.out.println(user.toString());

        //TODO: check null
        return user;
    }

    public User createCustomer(User user) {
        System.out.println(user.toString());

        // Check if email is already registered
        if (userRepository.findByEmail(user.getEmail()) != null) {
            System.out.println("We already got a user with this email: " + user.getEmail());
            return null;
        } else {
            System.out.println("Starting to create new user with id: " + user.getUserId());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(true);
            return userRepository.save(user);
        }
    }
}


