package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.AutenticacaoDto;
import med.voll.api.domain.Usuario;
import med.voll.api.infra.security.TokenService;
import med.voll.api.dto.TokenJwtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController { //Autenticação do usuário

    @Autowired
    private AuthenticationManager manager; //Essa classe é do spring boot responsável por autenticar as credenciais do usuário

    @Autowired
    private TokenService tokenService; //Essa classe é responsável por gerar token

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDto dados) { //a responsabilidade desse método é autenticar as credenciais do usuário e devolver um token como resposta
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); //aqui é um dto do spring
        var authentication = manager.authenticate(authenticationToken); //verifica as credenciais do usuário e, se forem válidas, retorna um objeto Authentication, representando o usuário autenticado.

        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJwtDto(tokenJwt));
    }

}
