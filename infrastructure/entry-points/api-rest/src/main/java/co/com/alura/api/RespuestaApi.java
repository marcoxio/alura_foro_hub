package co.com.alura.api;

import co.com.alura.jpa.respuestas.DatosCrearRespuesta;
import co.com.alura.jpa.respuestas.DatosEditarRespuesta;
import co.com.alura.jpa.respuestas.RespuestaService;
import co.com.alura.jpa.topicos.EstadoTopicoRespuesta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/respuestas")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class RespuestaApi {

    private final RespuestaService service;

    @PostMapping
    @Transactional
    public ResponseEntity crearRespuesta(@RequestBody @Valid DatosCrearRespuesta datos) {
        var response = service.crearRespuesta(datos);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid DatosEditarRespuesta datos) {
        var response = service.editarRespuesta(datos);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity cambiarEstadoRespuesta(@PathVariable("id") Long id) {
        EstadoTopicoRespuesta response = service.marcarDesmarcarComoSolucion(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable("id") Long id) {
        service.eliminarRespuesta(id);
        return ResponseEntity.ok().build();
    }
}
