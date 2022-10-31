package com.yurilacerda.forum.controller;

import com.yurilacerda.forum.controller.dto.TopicoDto;
import com.yurilacerda.forum.controller.dto.TopicoInputDto;
import com.yurilacerda.forum.modelo.Topico;
import com.yurilacerda.forum.repository.CursoRepository;
import com.yurilacerda.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){
        if (nomeCurso == null) {
            return TopicoDto.converter(topicoRepository.findAll());
        }
        return TopicoDto.converter(topicoRepository.findByCursoNome(nomeCurso));
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoInputDto form, UriComponentsBuilder uriBuilder) {
        Topico t = form.converter(cursoRepository);
        topicoRepository.save(t);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(t.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(t));
    }

}
