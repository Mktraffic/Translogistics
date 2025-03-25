package com.translogistics.translogistics.service;

import com.translogistics.translogistics.dto.UsuarioDTO;
import com.translogistics.translogistics.mapper.UsuarioMapper;
import com.translogistics.translogistics.model.Usuario;
import com.translogistics.translogistics.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> findAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO addUsuarioInDB(UsuarioDTO usuarioDTO) {
        System.out.println("Usuario antes de persistir: " + usuarioDTO);
        System.out.println("Contraseña antes de persistir: " + usuarioDTO.getUser_password());
        Usuario usuarioGuardado = usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO));
        return usuarioMapper.toDTO(usuarioGuardado);
    }

    public String validateUserByUserName(String userName, String password) {
        List<UsuarioDTO> userList = findAllUsuarios();
        boolean reponse = false;
        String userRol = "";
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUser_name().equals(userName) && userList.get(i).getUser_password().equals(password)){
                reponse=true;
                userRol = userList.get(i).getRol().getNombreRol();
                break;
            }
        }
        return reponse+","+userRol;
    }

}
