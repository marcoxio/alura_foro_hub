package co.com.alura.api;

import co.com.alura.jpa.curso.CursoService;
import co.com.alura.jpa.curso.DatosActualizarCurso;
import co.com.alura.jpa.curso.DatosCurso;
import co.com.alura.jpa.curso.DatosCrearCurso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/cursos")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class CursoApi {
    private final CursoService cursoService;

    @PostMapping
    @Transactional
    @Operation(summary = "Crea un curso")
    public ResponseEntity crearCurso(@RequestBody @Valid DatosCrearCurso datos) {
        var response = cursoService.crearCurso(datos);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza un curso")
    public ResponseEntity actualizarCurso(@RequestBody @Valid DatosActualizarCurso datos) {
        var response = cursoService.actualizarCurso(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un curso a partir del ID")
    public ResponseEntity cambiarEstadoCurso(@PathVariable("id") Long id){
        cursoService.cambiarEstado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Busca todos los cursos")
    public ResponseEntity<Page<DatosCurso>> listarCursos(@PageableDefault(size = 10) Pageable paginacion) {
        var response = cursoService.findAllByActivoTrue(paginacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca un curso a partir del ID")
    public ResponseEntity mostrarCurso(@PageableDefault(size = 10)Pageable paginacion,
                                       @PathVariable("id") @Valid Long id) {
        var response = cursoService.mostrarCurso(id, paginacion);
        return ResponseEntity.ok(response);
    }
}
