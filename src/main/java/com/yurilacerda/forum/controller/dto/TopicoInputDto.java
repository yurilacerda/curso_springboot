package com.yurilacerda.forum.controller.dto;

import com.yurilacerda.forum.modelo.Curso;
import com.yurilacerda.forum.modelo.Topico;
import com.yurilacerda.forum.repository.CursoRepository;
import com.yurilacerda.forum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicoInputDto {

    @NotNull @NotEmpty @Length(min=5)
    private String titulo;
    @NotNull @NotEmpty @Length(min=10)
    private String mensagem;
    @NotNull @NotEmpty
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository repository) {
        Curso c = repository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, c);
    }
}
