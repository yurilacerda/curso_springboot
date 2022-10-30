package com.yurilacerda.forum.controller;

import com.yurilacerda.forum.controller.dto.TopicoDto;
import com.yurilacerda.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @RequestMapping("/topicos")
    public List<TopicoDto> lista(String nomeCurso){
        if (nomeCurso == null) {
            return TopicoDto.converter(topicoRepository.findAll());
        }
        return TopicoDto.converter(topicoRepository.findByCursoNome(nomeCurso));

    }

}
