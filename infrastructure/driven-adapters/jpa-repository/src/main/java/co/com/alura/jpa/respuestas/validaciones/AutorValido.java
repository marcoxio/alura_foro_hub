package co.com.alura.jpa.respuestas.validaciones;

import co.com.alura.jpa.respuestas.DatosCrearRespuesta;
import co.com.alura.model.errores.ValidacionDeIntegridad;
import co.com.alura.security.usuarios.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AutorValido implements ValidadorRespuesta {

    private final UsuarioRepository usuarioRepository;

    @Override
    public void validar(DatosCrearRespuesta datos) {
        if (datos.idAutor() == null || !usuarioRepository.existsById(datos.idAutor())) {
            throw new ValidacionDeIntegridad("Autor no encontrado");
        }
    }
}
