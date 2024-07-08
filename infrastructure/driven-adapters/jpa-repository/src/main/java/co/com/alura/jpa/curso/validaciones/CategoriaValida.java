package co.com.alura.jpa.curso.validaciones;

import co.com.alura.jpa.curso.DatosCrearCurso;
import co.com.alura.model.errores.ValidacionDeIntegridad;
import co.com.alura.model.errores.gateways.Validador;
import org.springframework.stereotype.Component;

@Component
public class CategoriaValida implements ValidadorCurso {

    @Override
    public void validar(DatosCrearCurso datos) {
        if (datos.categoria() == null) {
            throw new ValidacionDeIntegridad("Debe asignarle una categoria al curso");
        }
        //Validar Enum

    }
}
