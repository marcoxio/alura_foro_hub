package co.com.alura.jpa.curso;

import jakarta.validation.constraints.NotNull;
public record DatosCrearCurso(
        @NotNull
        String nombre,
        @NotNull
        Categoria categoria
) {
}
