package com.springbatch.processadorvalidacao.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.processadorvalidacao.dominio.Cliente;

@Configuration
public class ProcessadorValidacaoProcessorConfig {
	@Bean
	public ItemProcessor<Cliente, Cliente> procesadorValidacaoProcessor() {
		BeanValidatingItemProcessor<Cliente> processor = new BeanValidatingItemProcessor<>();
		//Processa os itens removendo os que possuem erros na validação
		processor.setFilter(true);
		return processor;
	}
}
