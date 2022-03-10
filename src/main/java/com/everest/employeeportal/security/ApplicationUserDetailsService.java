package com.everest.employeeportal.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component("userDetailsService")
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService{
    public final UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> byUserName = userRepo.findByUsername(username);
        if(byUserName.isEmpty())
        {
            throw new UsernameNotFoundException("User with name:"+username+"not found");
        }
        final User applicationUser = byUserName.get();
        System.out.println("password: "+applicationUser.getPassword());
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(applicationUser.getRole()));
        return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), applicationUser.getPassword(), authorities);
    }
}
