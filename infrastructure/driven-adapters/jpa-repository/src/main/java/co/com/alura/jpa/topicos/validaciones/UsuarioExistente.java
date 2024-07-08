package co.com.alura.jpa.topicos.validaciones;

import co.com.alura.jpa.topicos.DatosCrearTopico;
import co.com.alura.model.errores.gateways.Validador;
import co.com.alura.security.usuarios.UsuarioRepository;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioExistente implements ValidadorTopico {

    private final UsuarioRepository usuarioRepository;

    @Override
    public void validar(DatosCrearTopico datos) {
        var usuario = usuarioRepository.existsById(datos.idAutor());

        if (!usuario) {
            new ValidationException("Autor no registrado.");
        }

    }
}
