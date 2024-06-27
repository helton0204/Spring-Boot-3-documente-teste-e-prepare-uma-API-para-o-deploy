package med.voll.api.infra.security;

import med.voll.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService { //essa classe representa o serviço de autenticação e essa classe vai ser chamada automaticamente pelo spring quando o usuário fizer a autenticação no projeto

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //esse método o spring chama automaticamente sempre que o usuário fizer login
        return repository.findByLogin(username);
    }

}
