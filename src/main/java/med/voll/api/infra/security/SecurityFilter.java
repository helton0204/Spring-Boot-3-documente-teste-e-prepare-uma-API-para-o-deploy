package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter { //essa classe intercepta todas as requisições para e verificar se há um token JWT válido no cabeçalho de autorização (Authorization).

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        if(tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            var usuario = repository.findByLogin(subject);
            //aqui faz a autenticação de um usuário logado (possui token) no sistema
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); //isso é um dto do spring
            SecurityContextHolder.getContext().setAuthentication(authentication); //indica se o usuário está autenticado.
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization"); //aqui resgata o token no cabeçalho da solicitação que vem do front-end
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer", "").trim();
        }
        return null;
    }
}
