package com.bcs.security.dao;

import com.bcs.security.model.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioDAO extends GenericRepo<Usuario, Long>{

    //select * from usuario where username = ?
    //Usuario findOneByUsername(String name);

    @Query("FROM Usuario u where u.name =:uname")
    Usuario findOneUsuario(@Param("uname") String name);
}
