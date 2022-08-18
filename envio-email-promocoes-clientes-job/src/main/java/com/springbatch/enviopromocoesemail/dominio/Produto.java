package com.springbatch.enviopromocoesemail.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

	private int id;
	private String nome;
	private String descricao;
	private Double preco;

}
