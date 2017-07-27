package br.com.ceuma.jwt;

import br.com.ceuma.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class TokenUtil {

    private static final long VALIDITY_TIME_MS = 2 * 60 * 60 * 1000; //DUAS HORAS DE VALIDADE

    @Autowired
    @Qualifier("HEADER_AUTHORIZATION")
    private String AUTH_HEADER_NAME;

    private String secret="M0NK3Y4P1";

    public Authentication verifyToken(HttpServletRequest request){
        final String token = request.getHeader(AUTH_HEADER_NAME);

        if(token != null && !token.isEmpty()){
            final TokenUser user = parseUserFromToken(token);
            if(user != null)
                return new UserAuthentication(user);
        }
        return null;
    }

    //Get User Info from the Token
    public TokenUser parseUserFromToken(String token){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return new TokenUser(new Usuario(Integer.valueOf(claims.get("codigo").toString()), claims.get("nome").toString()));
        }catch(JwtException ex){
            throw new JwtException(ex.getMessage());
        }
    }


    public String createTokenForUser(Usuario usuario){

        final String compact = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
                .setSubject(usuario.getCodigo().toString())
                .claim("codigo", usuario.getCodigo())
                .claim("nome", usuario.getNome())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return compact;

    }

}
