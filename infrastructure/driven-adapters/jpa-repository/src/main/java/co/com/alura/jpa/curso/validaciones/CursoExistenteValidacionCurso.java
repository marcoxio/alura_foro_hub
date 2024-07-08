package co.com.alura.jpa.curso.validaciones;

import co.com.alura.jpa.curso.CursoRepository;
import co.com.alura.jpa.curso.DatosCrearCurso;
import co.com.alura.model.errores.ValidacionDeIntegridad;
import co.com.alura.model.errores.gateways.Validador;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CursoExistenteValidacionCurso implements ValidadorCurso {

    private final CursoRepository repository;

    @Override
    public void validar(DatosCrearCurso datos) {
        if (repository.existsByNombre(datos.nombre())) {
            throw new ValidationException("Ya existe un curso con ese nombre");
        }
        if (datos.nombre() == null) {
            throw new ValidacionDeIntegridad("El nombre del curso no puede quedar vacio");
        }

    }
}
