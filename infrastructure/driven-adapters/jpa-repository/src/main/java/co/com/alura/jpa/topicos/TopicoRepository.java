package co.com.alura.jpa.topicos;

import co.com.alura.jpa.curso.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Boolean existsByTituloAndMensaje(String tituloTopico, String mensajeTopico);
    void removeById(Long id);
    Page<Topico> findAllByCurso(Curso curso, Pageable paginacion);

}
