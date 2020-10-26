/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.admis.security;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Adeep My IT Solution Private Limited
 */
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            response.addHeader("X-Content-Security-Policy","script-src 'self'");
            response.addHeader("Content-Security-Policy","font-src 'self'; style-src 'self'; img-src 'self' ; script-src 'self'; default-src 'self'; form-action 'self'; connect-src 'self' ");
            response.addHeader("Strict-Transport-Security","max-age=31536000 ; includeSubDomains");
            response.addHeader("X-Frame-Options", "DENY");
            response.addHeader("X-XSS-Protection", "1; mode=block");
            response.addHeader("X-Content-Type-Options", "nosniff");
            response.addHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Expires", "0");
            response.setHeader("Referrer-Policy", "origin");
            filterChain.doFilter(request, response);
    }

}