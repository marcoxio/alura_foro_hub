package co.com.alura.jpa.respuestas.validaciones;

import co.com.alura.jpa.respuestas.DatosCrearRespuesta;
import co.com.alura.jpa.respuestas.RespuestaRepository;
import co.com.alura.jpa.topicos.TopicoRepository;
import co.com.alura.model.errores.ValidacionDeIntegridad;
import co.com.alura.security.usuarios.UsuarioRepository;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RespuestaValida implements ValidadorRespuesta {

    private final RespuestaRepository respuestaRepository;

    private final UsuarioRepository usuarioRepository;

    private final TopicoRepository topicoRepository;

    @Override
    public void validar(DatosCrearRespuesta datos) {
        if (datos.mensaje() == null) {
            throw new ValidacionDeIntegridad("mensaje no encontrado");
        }

        if (respuestaRepository.existsByTopicoAndMensajeAndAutorRespuesta(
                topicoRepository.getReferenceById(datos.idTopico()),
                datos.mensaje(),
                usuarioRepository.getReferenceById(datos.idAutor()))) {
            throw new ValidationException("Ya existe una respuesta igual para el topico");
        }
    }
}
