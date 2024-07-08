package co.com.alura.jpa.topicos;

import co.com.alura.jpa.curso.CursoRepository;
import co.com.alura.jpa.topicos.validaciones.ValidadorTopico;
import co.com.alura.model.errores.gateways.Validador;
import co.com.alura.security.usuarios.UsuarioRepository;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    private final List<ValidadorTopico> validarTopico;

    public Page<DatosTopico> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosTopico::new);
    }
//
    public DatosTopico crearTopico(DatosCrearTopico datos) {

        validarTopico.forEach(v -> v.validar(datos));

        var titulo = datos.titulo();
        var mensaje = datos.mensaje();
        var autor = usuarioRepository.getReferenceById(datos.idAutor());
        var curso = cursoRepository.getReferenceById(datos.idCurso());

        var topico = new Topico(titulo, mensaje, autor, curso);


        topicoRepository.save(topico);

        return new DatosTopico(topico);
    }

    public DatosTopico actualizarTopico(DatosActualizarTopico datos) {

        var topico = topicoRepository.getReferenceById(datos.id());
        topico.actualirTopico(datos);

        return new DatosTopico(topico);
    }

    public String eliminarTopico(Long id) {
        var topico = topicoRepository.findById(id);
        if (!topico.isPresent() || id == null) {
            throw new ValidationException("No existe topico con id = " + id);
        }

        topicoRepository.removeById(id);

        return "Topico eliminado correctamente.";
    }
}
