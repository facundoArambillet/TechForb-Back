package com.TF.TechForb.service;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.User.User;
import com.TF.TechForb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String dniNumber) throws UsernameNotFoundException {
        try {
            Optional<User> authUser = userRepository.findByDocumentNumber(Integer.parseInt(dniNumber));
            if(authUser.isPresent()) {
                return UserDetailsImpl.build(authUser.get());
            } else {
                throw new TechForbException("Token invalid", null, HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (TechForbException ex) {
            throw ex;
        }


    }
}
