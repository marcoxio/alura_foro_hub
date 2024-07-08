package co.com.alura.jpa.topicos;


import co.com.alura.jpa.respuestas.DatosRespuesta;
import org.springframework.data.domain.Page;

public record DatosTopicoRespuestas(DatosTopico topico, Page<DatosRespuesta> respuestas) {
}
