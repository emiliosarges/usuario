package com.souemilio.usuario.business;

import com.souemilio.usuario.business.converter.UsuarioConverter;
import com.souemilio.usuario.business.dto.UsuarioDTO;
import com.souemilio.usuario.infrastructure.entity.Usuario;
import com.souemilio.usuario.infrastructure.exceptions.ConflictException;
import com.souemilio.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    //@RequiredArgsConstructor está incluindo um construtor com argumentos para todas as variáveis finais,
    // então não precisamos criar um construtor manualmente. Lombok vai gerar o construtor automaticamente,
    // e o Spring vai injetar a dependência do UsuarioRepository.

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuarioEntity(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email ja existente" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email ja existente" + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

}
