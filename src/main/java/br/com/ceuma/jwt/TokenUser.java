package br.com.ceuma.jwt;

import br.com.ceuma.model.Usuario;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class TokenUser extends User {

    private Usuario usuario;

    public TokenUser(Usuario usuario){
        super(usuario.getCodigo().toString(), usuario.getSenha(), AuthorityUtils.createAuthorityList(usuario.getRole().get(0)));
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
