package co.com.alura.jpa.topicos;

import co.com.alura.jpa.curso.DatosCurso;
import org.springframework.data.domain.Page;

public record DatosCursoTopicos(DatosCurso curso, Page<DatosTopico> topicos) {

}
