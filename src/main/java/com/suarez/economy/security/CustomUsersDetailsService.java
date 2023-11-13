package com.suarez.economy.security;

import com.suarez.economy.domain.entities.Role;
import com.suarez.economy.domain.entities.User;
import com.suarez.economy.domain.repositories.RoleRepository;
import com.suarez.economy.domain.repositories.UserRepository;
import com.suarez.economy.security.model.UserPrincipal;
import com.suarez.economy.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUsersDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public CustomUsersDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    //Método para traernos una lista de autoridades por medio de una lista de roles
    public Collection<GrantedAuthority> mapToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    //Método para traernos un usuario con todos sus datos por medio de sus username
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        UserPrincipal userPrincipal = UserMapper.INSTANCE.userPrincipalFromUser(user);
        userPrincipal.setAuthorities(Collections.singleton(new SimpleGrantedAuthority("ADMIN")));
        return userPrincipal;
    }
}


