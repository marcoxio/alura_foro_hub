package co.com.alura.jpa.topicos;

import jakarta.validation.constraints.NotNull;

public record DatosCrearTopico(
        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        Long idAutor,
        @NotNull
        Long idCurso
) {
}
