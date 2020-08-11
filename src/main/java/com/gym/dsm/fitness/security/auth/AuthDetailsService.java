package com.gym.dsm.fitness.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserFactory userFactory;

    @Override
    public AuthDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userFactory.getAuthDetails(userEmail);
    }

}