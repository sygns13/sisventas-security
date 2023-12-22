package com.bcs.security.dao;

import com.bcs.security.model.entities.DatosUser;
import com.bcs.security.model.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DatosUserDAO extends GenericRepo<DatosUser, Long>{

    @Query("FROM DatosUser du where du.userId =:user_id")
    DatosUser findOneByUsuario(@Param("user_id") Long userId);
}
