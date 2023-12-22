package com.bcs.security.oauth;

import com.bcs.security.dao.DatosUserDAO;
import com.bcs.security.dao.UsuarioDAO;
import com.bcs.security.model.entities.DatosUser;
import com.bcs.security.model.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
@RequestScope
public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private DatosUserDAO datosUserDAO;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        String name = oAuth2Authentication.getName();
        Usuario user = usuarioDAO.findOneUsuario(name);
        DatosUser datosUser = datosUserDAO.findOneByUsuario(user.getId());
        user.setDatosUser(datosUser);

        //Usuario user = (CustomUserDetails) oAuth2Authentication.getPrincipal();
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("auth_level_1", user.getTipoUser().getId());
        additionalInfo.put("auth_level_2", user.getEmpresa() != null && user.getEmpresa().getId() != null ? user.getEmpresa().getId() : null);
        additionalInfo.put("auth_level_3", user.getId());
        additionalInfo.put("company_doc", user.getEmpresa() != null && user.getEmpresa().getId() != null ? user.getEmpresa().getRuc() : null);
        additionalInfo.put("company_name", user.getEmpresa() != null && user.getEmpresa().getId() != null ? user.getEmpresa().getRazonsocial() : null);
        additionalInfo.put("user_type_doc_id", user.getDatosUser() != null && user.getDatosUser().getTipoDocumento() != null ? user.getDatosUser().getTipoDocumento().getId() : null);
        additionalInfo.put("user_type_doc_desc", user.getDatosUser() != null && user.getDatosUser().getTipoDocumento() != null ? user.getDatosUser().getTipoDocumento().getTipo() : null);
        additionalInfo.put("user_doc", user.getDatosUser() != null ? user.getDatosUser().getDocumento() : null);
        additionalInfo.put("user_names", user.getDatosUser() != null ? user.getDatosUser().getNombres() : null);
        additionalInfo.put("user_last_name_1", user.getDatosUser() != null ? user.getDatosUser().getApellidoMaterno() : null);
        additionalInfo.put("user_last_name_2", user.getDatosUser() != null ? user.getDatosUser().getApellidoMaterno() : null);
        additionalInfo.put("user_email", user.getEmail());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);

        //Object principal = oAuth2Authentication.getPrincipal();
        return oAuth2AccessToken;
    }
}
