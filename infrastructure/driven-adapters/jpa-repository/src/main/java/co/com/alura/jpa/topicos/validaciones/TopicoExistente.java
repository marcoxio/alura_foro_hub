package co.com.alura.jpa.topicos.validaciones;

import co.com.alura.jpa.topicos.DatosCrearTopico;
import co.com.alura.jpa.topicos.TopicoRepository;
import co.com.alura.model.errores.gateways.Validador;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TopicoExistente implements ValidadorTopico {

    private final TopicoRepository topicoRepository;


    @Override
    public void validar(DatosCrearTopico datos) {
        var tituloExistente = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());

        if(tituloExistente != null && tituloExistente) {
            throw new ValidationException("Ya existe un topico con ese titulo.");
        }
    }
}
