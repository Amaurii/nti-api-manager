package br.com.ceuma.security;


import br.com.ceuma.jwt.TokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;

public class VerifyTokenFilter extends GenericFilterBean{

    private TokenUtil tokenUtil;

    public VerifyTokenFilter(TokenUtil tUtil){
        this.tokenUtil = tUtil;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        try{
            Authentication auth = this.tokenUtil.verifyToken(request);
            if(auth != null){
                SecurityContextHolder.getContext().setAuthentication(auth);
            }else{
                SecurityContextHolder.getContext().setAuthentication(null);
            }
            chain.doFilter(req, res);
        }catch(JwtException ex){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }finally {
            SecurityContextHolder.getContext().setAuthentication(null);
            return;
        }
    }
}
