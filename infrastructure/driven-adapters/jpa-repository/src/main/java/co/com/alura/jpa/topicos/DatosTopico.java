package co.com.alura.jpa.topicos;


import java.time.LocalDateTime;

public record DatosTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status,
        String autor,
        String  curso
) {
    public DatosTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutorTopico().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
