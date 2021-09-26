package com.bcs.security.service.impl;

import com.bcs.security.dao.UsuarioDAO;
import com.bcs.security.model.entities.Usuario;

import com.bcs.security.service.UserService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService<Usuario, Long> {

    @Autowired
    private UsuarioDAO client;
    private Logger log = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    /*
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = client.findOneByUsername(username);
        if (usuario==null ) {throw new UsernameNotFoundException(String.format("Usuario no existe", username));}


        List<GrantedAuthority> authorities = usuario.getRoles().stream()

                .map(role-> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority-> log.info("Role: "+ authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(),true, true, true ,
                authorities);

        return  new User(usuario.getName(), usuario.getPassword(), usuario.getActivo().equals(Constantes.CANTIDAD_UNIDAD_INTEGER),true, true, true ,
                authorities);
    }*/

    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = client.findOneUsuario(username);
        if (usuario==null ) {throw new UsernameNotFoundException(String.format("Usuario no existe", username));}

/*
        List<GrantedAuthority> authorities = usuario.getRoles().stream()

                .map(role-> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority-> log.info("Role: "+ authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(),true, true, true ,
                authorities);*/

        return  usuario;
    }
}
