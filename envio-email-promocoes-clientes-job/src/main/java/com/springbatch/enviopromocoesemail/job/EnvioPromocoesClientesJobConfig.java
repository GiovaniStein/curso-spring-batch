package com.springbatch.enviopromocoesemail.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
public class EnvioPromocoesClientesJobConfig {
	private final JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job envioEmailClientesJob(Step envioEmailClientesStep) {
		return jobBuilderFactory
				.get("envioEmailClientesJob")
				.start(envioEmailClientesStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
