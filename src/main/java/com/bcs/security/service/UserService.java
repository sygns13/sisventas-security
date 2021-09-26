package com.bcs.security.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService<Usuario, Long>  {

    //UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    Usuario loadUserByUsername(String username) throws UsernameNotFoundException;

}
