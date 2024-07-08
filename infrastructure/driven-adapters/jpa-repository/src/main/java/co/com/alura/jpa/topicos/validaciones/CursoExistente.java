package co.com.alura.jpa.topicos.validaciones;

import co.com.alura.jpa.curso.CursoRepository;
import co.com.alura.jpa.topicos.DatosCrearTopico;
import co.com.alura.model.errores.ValidacionDeIntegridad;
import co.com.alura.model.errores.gateways.Validador;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CursoExistente implements ValidadorTopico {

    private final CursoRepository cursoRepository;

    @Override
    public void validar(DatosCrearTopico datos) {
        if (datos.idCurso() == null) {
            throw new ValidacionDeIntegridad("El topico debe tener un curso asignado");
        }
        var curso = cursoRepository.findById(datos.idCurso());

        if (!curso.isPresent()) {
            throw new ValidationException("El curso al que desea agregar el topico no existe.");
        }
    }
}
