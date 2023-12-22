package com.bcs.security.service.impl;


import com.bcs.security.dao.DatosUserDAO;
import com.bcs.security.dao.UsuarioDAO;
import com.bcs.security.model.entities.DatosUser;
import com.bcs.security.model.entities.Usuario;

import com.bcs.security.oauth.CustomUserDetails;
import com.bcs.security.utils.Constantes;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private DatosUserDAO datosUserDAO;
    private Logger log = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.findOneUsuario(name);
        if (usuario==null ) {throw new UsernameNotFoundException(String.format("Usuario no existe", name));}

        /*
        DatosUser datosUser = datosUserDAO.findOneByUsuario(usuario.getId());
        usuario.setDatosUser(datosUser);
        return new CustomUserDetails(usuario);
         */

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(usuario.getTipoUser().getNombre()));

        return  new User(usuario.getName(), usuario.getPassword(), usuario.getActivo().equals(Constantes.CANTIDAD_UNIDAD_INTEGER),true, true, true ,
        roles);




/*
        List<GrantedAuthority> authorities = new AbstractList<GrantedAuthority>() {
            @Override
            public GrantedAuthority get(int index) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }
        };*/

        /*
        List<GrantedAuthority> authorities = usuario.getTipoUser()
                .
                .map(role-> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority-> log.info("Role: "+ authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(),true, true, true ,
                authorities);*/

    }

}
