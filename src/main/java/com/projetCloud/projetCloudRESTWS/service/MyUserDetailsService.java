package com.projetCloud.projetCloudRESTWS.service;

import com.projetCloud.projetCloudRESTWS.model.CustomUserDetails;
import com.projetCloud.projetCloudRESTWS.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userService.getUserByMail(username);
        if(!optUser.isPresent())
            throw new UsernameNotFoundException("User not found!");
        User user = optUser.get();
        return new CustomUserDetails(user);
    }
}
