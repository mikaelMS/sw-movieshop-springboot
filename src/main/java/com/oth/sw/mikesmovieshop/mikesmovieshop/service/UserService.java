package com.oth.sw.mikesmovieshop.mikesmovieshop.service;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;
import com.oth.sw.mikesmovieshop.mikesmovieshop.repository.UserRepository;
import de.othr.sw.haeusler.followspot.DTOs.CustomerDetail;
import de.othr.sw.haeusler.followspot.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@Qualifier("labresources")
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private String clientId = "CLI_MS20200629123333912";

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplateBuilder restServiceClient;

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
        User user = userRepository.findByEmail(email);
        System.out.println(user.toString());

        return user;
    }

    public User registerCustomer(User user) {
        // Check if email is already registered
        if (userRepository.findByEmail(user.getEmail()) != null) {
            System.out.println("We already got a user with this email: " + user.getEmail());
            return null;
        } else {
            System.out.println("Starting to create new user with id: " + user.getUserId());
            CustomerDetail details = new CustomerDetail();
            details.setClientID(clientId);
            details.setEmail(user.getEmail());
            details.setPassword(user.getPassword());
            details.setFirstName("");
            details.setLastName("");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<CustomerDetail> requestBody = new HttpEntity<>(details, headers);
            Customer newCustomer = null;

            try {
                newCustomer = restServiceClient.build()
                        .postForObject("http://im-codd:8851/customer/registration",
                                requestBody, Customer.class);
            } catch (HttpClientErrorException | NullPointerException e) {
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println();
            }
            assert newCustomer != null;
            System.out.println(newCustomer.toString());

            user.setActive(true);
            return userRepository.save(user);
        }




    }
}


