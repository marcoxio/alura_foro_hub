package co.com.alura.jpa.curso;

import co.com.alura.jpa.curso.validaciones.ValidadorCurso;
import co.com.alura.jpa.topicos.DatosTopico;
import co.com.alura.jpa.topicos.TopicoRepository;
import co.com.alura.model.errores.ValidacionDeIntegridad;
import co.com.alura.model.errores.gateways.Validador;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CursoService {
    private final CursoRepository cursoRepository;

    private final TopicoRepository topicoRepository;

    private final List<ValidadorCurso> validadorCursos;

    public DatosCurso crearCurso(DatosCrearCurso datos) {
        validadorCursos.forEach(v -> v.validar(datos));
        var curso = new Curso(datos.nombre(), datos.categoria());
        cursoRepository.save(curso);
        return new DatosCurso(curso);
    }

    public DatosCurso actualizarCurso(DatosActualizarCurso datos) {
        cursoExistente(datos.id());
        var curso = cursoRepository.getReferenceById(datos.id());
        curso.actualizar(datos);
        return new DatosCurso(curso);
    }

    public DatosCurso cambiarEstado(Long id) {
        cursoExistente(id);
        var curso = cursoRepository.getReferenceById(id);
        curso.eliminar();
        return new DatosCurso(curso);
    }

    private void cursoExistente(Long id) {
        if (id == null) {
            throw new ValidationException("Debe proporcionar el id del curso");
        }

        if (!cursoRepository.existsById(id)) {
            throw new ValidacionDeIntegridad("Debe proporcionar el ID de curso valido");
        }
    }
    public Page<DatosCurso> listarCursos(Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(DatosCurso::new);
    }

    public Page<DatosCurso> findAllByActivoTrue(Pageable paginacion) {
        return cursoRepository.findAllByActivoTrue(paginacion).map(DatosCurso::new);
    }

    public DatosCursoTopicos mostrarCurso(Long id, Pageable paginacion) {
        cursoExistente(id);
        var curso = cursoRepository.getReferenceById(id);
        var topicos = topicoRepository.findAllByCurso(curso, paginacion).map(DatosTopico::new);
        return new DatosCursoTopicos(new DatosCurso(curso), topicos);
    }

}
