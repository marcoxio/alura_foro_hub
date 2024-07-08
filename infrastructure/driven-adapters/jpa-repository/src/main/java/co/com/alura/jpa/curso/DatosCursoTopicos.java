package co.com.alura.jpa.curso;

import co.com.alura.jpa.topicos.DatosTopico;
import org.springframework.data.domain.Page;

public record DatosCursoTopicos(DatosCurso curso, Page<DatosTopico> topicos) {
}
