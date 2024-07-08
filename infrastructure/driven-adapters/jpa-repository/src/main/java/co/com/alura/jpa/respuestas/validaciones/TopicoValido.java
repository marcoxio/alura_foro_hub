package co.com.alura.jpa.respuestas.validaciones;

import co.com.alura.jpa.respuestas.DatosCrearRespuesta;
import co.com.alura.jpa.topicos.TopicoRepository;
import co.com.alura.model.errores.ValidacionDeIntegridad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TopicoValido implements ValidadorRespuesta {

    private final TopicoRepository topicoRepository;

    @Override
    public void validar(DatosCrearRespuesta datos) {
        if (datos.idTopico() == null || !topicoRepository.existsById(datos.idTopico())) {
            throw new ValidacionDeIntegridad("Topico no encontrado");
        }
    }
}
