package com.souemilio.usuario.business;

import com.souemilio.usuario.business.converter.UsuarioConverter;
import com.souemilio.usuario.business.dto.UsuarioDTO;
import com.souemilio.usuario.infrastructure.entity.Usuario;
import com.souemilio.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    //@RequiredArgsConstructor está incluindo um construtor com argumentos para todas as variáveis finais,
    // então não precisamos criar um construtor manualmente. Lombok vai gerar o construtor automaticamente,
    // e o Spring vai injetar a dependência do UsuarioRepository.

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuarioEntity(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

}
