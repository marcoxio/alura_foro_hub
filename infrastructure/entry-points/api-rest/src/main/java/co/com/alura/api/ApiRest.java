package co.com.alura.api;

import co.com.alura.jpa.topicos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/topicos", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class ApiRest {
    private final TopicoService topicoService;


    @GetMapping
    @Operation(summary = "Obtiene el listado de los topicos")
    public ResponseEntity<Page<DatosTopico>> listarTopicos(@PageableDefault(size = 10)Pageable paginacion){
        var response = topicoService.listarTopicos(paginacion);
        return ResponseEntity.ok(response);
    }



    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo topico")
    public ResponseEntity crear(@RequestBody @Valid DatosCrearTopico datos, UriComponentsBuilder uriBuilder) {
        var response = topicoService.crearTopico(datos);
        URI location = uriBuilder.path("/topicos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza un topico")
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos) {
        var response = topicoService.actualizarTopico(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un topico")
    public ResponseEntity eliminarTopico(@PathVariable("id") Long id) {
        var response = topicoService.eliminarTopico(id);
        return ResponseEntity.ok(response);
    }
}
