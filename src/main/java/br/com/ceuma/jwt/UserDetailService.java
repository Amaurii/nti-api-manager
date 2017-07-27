package br.com.ceuma.jwt;

import br.com.ceuma.model.Usuario;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailService implements UserDetailsService {

    /**
     * TODO IMPLEMENTAR DAO E RESPECTIVO METODO PARA BUSCAR USUARIO PELO USERNAME
     */


    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        TokenUser tokenUser;
        try{
            Usuario usuario = new Usuario(1,nome);
            tokenUser = new TokenUser(usuario);
        }catch(Exception ex){
            throw new UsernameNotFoundException("Erro ao logar com o usuario", ex);
        }
        detailsChecker.check(tokenUser);
        return tokenUser;
    }
}
