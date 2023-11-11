package com.suarez.economy.service.auth;

import com.suarez.economy.domain.entities.Client;
import com.suarez.economy.domain.entities.User;
import com.suarez.economy.domain.repositories.ClientRepository;
import com.suarez.economy.domain.repositories.UserRepository;
import com.suarez.economy.exception.ClientNotFoundException;
import com.suarez.economy.exception.UserNotFoundException;
import com.suarez.economy.util.mappers.ClientMapper;
import com.suarez.economy.util.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    /**
     * If the username is an email, then it will search for a client with that
     * email, if it doesn't
     * find one, it will throw a ClientNotFoundException. If it finds one, it will
     * return a
     * UserPrincipal object with the client's information. If the username is not an
     * email, then it
     * will search for a user with that username, if it doesn't find one, it will
     * throw a
     * UserNotFoundException. If it finds one, it will return a UserPrincipal object
     * with the user's
     * information.
     *
     * @param username The username of the user to load.
     * @return UserPrincipal
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
                "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(username);
        if (matcher.matches()) {
            Client client = clientRepository.findByEmail(username)
                    .orElseThrow(() -> new ClientNotFoundException(username));
            return ClientMapper.INSTANCE.userPrincipalFromClient(client);
        }
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return UserMapper.INSTANCE.userPrincipalFromUser(user);
    }

}
