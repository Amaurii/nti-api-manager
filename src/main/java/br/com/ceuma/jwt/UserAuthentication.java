package br.com.ceuma.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthentication implements Authentication {

    @JsonProperty
    private final TokenUser user;

    @JsonProperty
    private boolean authenticated = true;

    public UserAuthentication(TokenUser tokenUser){
        this.user = tokenUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    @JsonProperty
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public TokenUser getDetails() {
        return user;
    }

    @Override
    @JsonProperty
    public Object getPrincipal() {
        return user.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    @JsonProperty
    public String getName() {
        return user.getUsername();
    }
}
