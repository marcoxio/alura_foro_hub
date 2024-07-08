package co.com.alura.jpa.curso;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Boolean activo;

    public Curso(String nombre, Categoria categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.activo = true;
    }

    public void actualizar(DatosActualizarCurso datos) {
        if (datos.nombre() != null) {
            this.nombre= datos.nombre();
        }

        if (datos.categoria() != null) {
            this.categoria = datos.categoria();
        }
    }

    public void eliminar() {
        this.activo = false;
    }
}
