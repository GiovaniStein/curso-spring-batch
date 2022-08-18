package com.springbatch.enviopromocoesemail.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteresseProdutoCliente {

	private Cliente cliente;
	private Produto produto;

}
