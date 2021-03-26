package br.com.cineclube.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Filme {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message="Nome campo obrigatorio")
	@Size(min=1, max=50, message="Minimo de {min} caracteres em maximo de {max}")
	private String nome; // ""
	
	@NotNull
	@Past // ==> data de nascimento da Pessoa deve ser validada com Past (valida datas anteriores ao data agora)
	// no caso de lancamento do filme essa regra nem sempre eh interessante pois um filme pode
	// estar programado para lancamento no futuro (mas serve aqui como exemplo de como validar)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate lancamento;
	
	// relacionamento N-N entre pessoa e filme
	// @ManyToMany
	
	@NotBlank
	private String categoria; // drama, action, ...
	
	private Float nota; // 0..10

	public Filme() {}

	public Filme(String nome, Float nota, LocalDate lancamento, String categoria) {
		this.nome = nome;
		this.lancamento = lancamento;
		this.nota = nota;
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}


	public LocalDate getLancamento() {
		return lancamento;
	}

	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
