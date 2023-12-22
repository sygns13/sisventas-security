package com.bcs.security.oauth;

import com.bcs.security.model.entities.Usuario;
import com.bcs.security.utils.Constantes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public class CustomUserDetails extends Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> attributes;
    private String name;
    private Map<String, Object> claims;

    public CustomUserDetails(Usuario user) {

        super(user);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> c = new ArrayList<>();
        c.add(new SimpleGrantedAuthority(super.getTipoUser().getNombre()));
        return c;
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.getActivo().equals(Constantes.CANTIDAD_UNIDAD_INTEGER);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getClaims() {
        return claims;
    }

    public void setClaims(Map<String, Object> claims) {
        this.claims = claims;
    }
}
