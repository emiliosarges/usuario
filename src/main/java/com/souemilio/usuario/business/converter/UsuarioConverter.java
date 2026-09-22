package com.souemilio.usuario.business.converter;

import com.souemilio.usuario.business.dto.EnderecoDTO;
import com.souemilio.usuario.business.dto.TelefoneDTO;
import com.souemilio.usuario.business.dto.UsuarioDTO;
import com.souemilio.usuario.infrastructure.entity.Endereco;
import com.souemilio.usuario.infrastructure.entity.Telefone;
import com.souemilio.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    // Converte os objetos recebidos pela API (DTOs) para entidades persistidas.
    public Usuario paraUsuarioEntity(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraEnderecoEntityList(usuarioDTO.getEnderecos()))
                .build();
    }

    public List<Endereco> paraEnderecoEntityList(List<EnderecoDTO> enderecoDTOs) {
        return enderecoDTOs.stream().map(this::paraEnderecoEntity).toList();
    }

    public Endereco paraEnderecoEntity(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .telefones(paraTelefoneEntityList(enderecoDTO.getTelefones()))
                .build();
    }

    public List<Telefone> paraTelefoneEntityList(List<TelefoneDTO> telefoneDTOs) {
        return telefoneDTOs.stream().map(this::paraTelefoneEntity).toList();
    }

    public Telefone paraTelefoneEntity(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    // Converte as entidades persistidas para os DTOs devolvidos pela API.
    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraEnderecoDTOList(usuario.getEnderecos()))
                .build();
    }

    public List<EnderecoDTO> paraEnderecoDTOList(List<Endereco> enderecos) {
        return enderecos.stream().map(this::paraEnderecoDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .telefones(paraTelefoneDTOList(endereco.getTelefones()))
                .build();
    }

    public List<TelefoneDTO> paraTelefoneDTOList(List<Telefone> telefones) {
        return telefones.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }
}
