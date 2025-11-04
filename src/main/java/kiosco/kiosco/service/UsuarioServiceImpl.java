package kiosco.kiosco.service;


import kiosco.kiosco.entidad.Usuario;
import kiosco.kiosco.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario iniciarSesion(Usuario usuario) {
        Optional<Usuario> usuario1 = usuarioRepository.findByNombre(usuario.getNombre());

        if (usuario1.isPresent()){
            throw new IllegalArgumentException("error en el nombre de usuario");
        }

        Usuario usuario2=usuario1.get();

        if (!usuario2.getContrasenia().equals(usuario.getContrasenia())){
            throw new IllegalArgumentException("error en la contrasenia");
        }

        return usuario2;
    }
}
