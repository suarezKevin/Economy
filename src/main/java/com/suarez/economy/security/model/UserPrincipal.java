package com.suarez.economy.security.model;

import com.suarez.economy.domain.entities.Institution;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
public class UserPrincipal implements UserDetails{

    private UUID id;
    private String identification;
    private String fullName;
    private String email;
    private String password;
    private String telephone;
    private Boolean status;
    private Institution institution;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * This function returns a collection of GrantedAuthority objects that represent
     * the roles that the
     * user has.
     *
     * @return A collection of GrantedAuthority objects.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * This function returns the password of the user
     *
     * @return The password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * This function returns the username of the user
     *
     * @return The username
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * This function returns true if the account is not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * This function returns true if the account is not locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * This function returns true if the user's credentials are not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * This function returns true if the user is enabled, and false if the user is
     * disabled.
     *
     * @return The status of the user.
     */
    @Override
    public boolean isEnabled() {
        return status;
    }

}
