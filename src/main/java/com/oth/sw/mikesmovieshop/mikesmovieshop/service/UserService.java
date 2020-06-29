package com.oth.sw.mikesmovieshop.mikesmovieshop.service;

import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.Role;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.User;
import com.oth.sw.mikesmovieshop.mikesmovieshop.entity.auth.UserRole;
import com.oth.sw.mikesmovieshop.mikesmovieshop.repository.RoleRepository;
import com.oth.sw.mikesmovieshop.mikesmovieshop.repository.UserRepository;
import com.oth.sw.mikesmovieshop.mikesmovieshop.repository.UserRoleRepository;
import de.othr.sw.haeusler.followspot.DTOs.CustomerDetail;
import de.othr.sw.haeusler.followspot.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
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

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private String clientId = "CLI_MS20200629123333912";

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplateBuilder restServiceClient;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        // search for id (email) in own db
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("onno");
                            throw new UsernameNotFoundException("User with email. " + email + " doesn't exist");
                        });
        System.out.println(user.toString());

        // get customer from identity provider
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Customer> entity = new HttpEntity<>(headers);

        ResponseEntity<Customer> customerEntity = null;

        try {
            customerEntity = restServiceClient.build()
                    .exchange("http://im-codd:8851/customer/get?clientId={clientId}&custId={userId}", HttpMethod.GET,
                            entity, Customer.class, clientId, user.getIdentityProviderCusId());

            Customer responseCustomer = customerEntity.getBody();
            assert responseCustomer != null;
            user.setPassword(responseCustomer.getPassword());

            return user;
        } catch (HttpClientErrorException e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();

            return null;
        }
    }

    public User registerCustomer(User user, String password) {
        // Check if email is already registered
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            System.out.println("We already got a user with this email: " + user.getEmail());
            return null;
        } else {
            System.out.println("Starting to create new user with id: " + user.getUserId());
            CustomerDetail details = new CustomerDetail();
            details.setClientID(clientId);
            details.setEmail(user.getEmail());
            details.setPassword(passwordEncoder.encode(password));
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

                user.setActive(true);
                user.setIdentityProviderCusId(newCustomer.getCustId());
                Role role = roleRepository.findByRoleId(1).orElse(null);
                UserRole userRole = new UserRole(user, role);
                User createdUser = userRepository.save(user);
                UserRole savedUserRole = userRoleRepository.save(userRole);
                user.setUserRole(savedUserRole);

                return createdUser;
            } catch (HttpClientErrorException | NullPointerException e) {
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println();

                return user;
            }
        }
    }
}


